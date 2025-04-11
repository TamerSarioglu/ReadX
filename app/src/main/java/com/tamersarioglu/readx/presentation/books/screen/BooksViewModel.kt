package com.tamersarioglu.readx.presentation.books.screen

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamersarioglu.readx.domain.usecase.GetBooksUseCase
import com.tamersarioglu.readx.presentation.books.BooksUiState
import com.tamersarioglu.readx.domain.model.SearchType
import com.tamersarioglu.readx.presentation.books.NetworkError
import com.tamersarioglu.readx.presentation.books.toNetworkError
import com.tamersarioglu.readx.domain.usecase.DEFAULT_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.tamersarioglu.readx.domain.model.Book
import com.tamersarioglu.readx.R

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val application: Application
) : ViewModel() {

    private val _uiState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _currentPage = MutableStateFlow(1)
    private val _searchType = MutableStateFlow<SearchType>(SearchType.General("tolkien"))
    private val _currentBooks = MutableStateFlow<List<Book>>(emptyList())
    private var hasMoreData = true

    init {
        loadBooks(isRefresh = false)
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun performSearch() {
        val query = _searchQuery.value
        if (query.isNotBlank()) {
            _searchType.value = SearchType.General(query)
        } else {
            _searchType.value = SearchType.General("")
        }
        resetAndLoadBooks()
    }

    fun loadNextPage() {
        if (_uiState.value !is BooksUiState.Success || (_uiState.value as BooksUiState.Success).isLoadingMore || !hasMoreData) {
            return
        }
        _currentPage.value++
        loadBooks(isRefresh = false)
    }

    fun refreshBooks() {
        resetAndLoadBooks()
    }

    private fun resetAndLoadBooks() {
        _currentPage.value = 1
        _currentBooks.value = emptyList()
        hasMoreData = true
        loadBooks(isRefresh = true)
    }

    private fun loadBooks(isRefresh: Boolean) {
        val pageToLoad = _currentPage.value

        val currentSuccessState = _uiState.value as? BooksUiState.Success
        _uiState.value = if (pageToLoad == 1 || isRefresh) {
            BooksUiState.Success(
                _currentBooks.value,
                isRefreshing = true,
                hasMoreData = hasMoreData
            )
        } else {
            currentSuccessState?.copy(isLoadingMore = true) ?: BooksUiState.Loading
        }

        viewModelScope.launch {
            getBooksUseCase(
                searchType = _searchType.value,
                page = pageToLoad,
                pageSize = DEFAULT_PAGE_SIZE
            ).collect { result ->
                result.fold(
                    onSuccess = { newBooks ->
                        hasMoreData = newBooks.isNotEmpty() && newBooks.size >= DEFAULT_PAGE_SIZE
                        val updatedBooks = if (pageToLoad == 1) {
                            newBooks
                        } else {
                            _currentBooks.value + newBooks
                        }
                        _currentBooks.value = updatedBooks
                        _uiState.value = BooksUiState.Success(
                            books = updatedBooks,
                            isRefreshing = false,
                            isLoadingMore = false,
                            hasMoreData = hasMoreData
                        )
                    },
                    onFailure = { error ->
                        val networkError = error as? NetworkError
                            ?: error.toNetworkError()

                        if (pageToLoad > 1 && currentSuccessState != null) {
                            _uiState.value = currentSuccessState.copy(
                                isLoadingMore = false,
                                hasMoreData = false
                            )
                        } else {
                            _uiState.value = BooksUiState.Error(
                                message = networkError.toUserFriendlyMessage(application.applicationContext),
                                cause = networkError
                            )
                        }
                        if (pageToLoad > 1) _currentPage.value = pageToLoad - 1
                        hasMoreData = false
                    }
                )
            }
        }
    }

    private fun NetworkError.toUserFriendlyMessage(context: Context): String {
        return when (this) {
            is NetworkError.NoInternet -> context.getString(R.string.error_no_internet)
            is NetworkError.ServerError -> context.getString(R.string.error_server)
            is NetworkError.NotFound -> context.getString(R.string.error_not_found)
            is NetworkError.Unknown -> message.takeUnless { it.isNullOrBlank() }
                ?: context.getString(R.string.error_unknown)
        }
    }
}