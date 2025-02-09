package com.tamersarioglu.readx.presentation.books.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamersarioglu.readx.domain.usecase.GetBooksUseCase
import com.tamersarioglu.readx.presentation.books.BooksUiState
import com.tamersarioglu.readx.domain.model.SearchType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.tamersarioglu.readx.presentation.books.components.SearchFilter

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _selectedFilter = MutableStateFlow(SearchFilter.GENERAL)
    val selectedFilter = _selectedFilter.asStateFlow()

    private val _currentPage = MutableStateFlow(1)
    private val _searchType = MutableStateFlow<SearchType>(SearchType.ByAuthor("tolkien"))

    init {
        loadBooks()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onFilterSelected(filter: SearchFilter) {
        _selectedFilter.value = filter
    }

    fun searchByTitle(title: String) {
        _searchType.value = SearchType.ByTitle(title)
        _currentPage.value = 1
        loadBooks()
    }

    fun searchByAuthor(author: String, sort: String? = null) {
        _searchType.value = SearchType.ByAuthor(author, sort)
        _currentPage.value = 1
        loadBooks()
    }

    fun loadNextPage() {
        _currentPage.value += 1
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _uiState.value = BooksUiState.Loading
            getBooksUseCase(
                searchType = _searchType.value,
                page = _currentPage.value
            ).collect { result ->
                _uiState.value = result.fold(
                    onSuccess = { books -> BooksUiState.Success(books) },
                    onFailure = { error -> BooksUiState.Error(error.message ?: "Unknown error") }
                )
            }
        }
    }

    fun onSearch() {
        if (_searchQuery.value.isNotBlank()) {
            viewModelScope.launch {
                _uiState.value = BooksUiState.Loading
                val searchType = when (_selectedFilter.value) {
                    SearchFilter.GENERAL -> SearchType.General(_searchQuery.value)
                    SearchFilter.TITLE -> SearchType.ByTitle(_searchQuery.value)
                    SearchFilter.AUTHOR -> SearchType.ByAuthor(_searchQuery.value)
                    SearchFilter.AUTHOR_SEARCH -> SearchType.AuthorSearch(_searchQuery.value)
                }
                _searchType.value = searchType
                _currentPage.value = 1
                loadBooks()
            }
        } else {
            // Reset to default Tolkien books when search is cleared
            _searchType.value = SearchType.ByAuthor("tolkien")
            _currentPage.value = 1
            loadBooks()
        }
    }
}