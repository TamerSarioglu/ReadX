package com.tamersarioglu.readx.presentation.books.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.tamersarioglu.readx.presentation.books.BookDetailsUiState

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: BookDetailsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (val state = uiState) {
            is BookDetailsUiState.Loading -> LoadingScreen()
            is BookDetailsUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    // Cover Image
                    if (state.bookDetails.covers.isNotEmpty()) {
                        AsyncImage(
                            model = state.bookDetails.covers.first(),
                            contentDescription = state.bookDetails.title,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Title
                    Text(
                        text = state.bookDetails.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Description
                    state.bookDetails.description?.let { description ->
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Subjects
                    Text(
                        text = "Subjects",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // General Subjects
                    if (state.bookDetails.subjects.general.isNotEmpty()) {
                        SubjectSection(
                            title = "General",
                            items = state.bookDetails.subjects.general
                        )
                    }

                    // Places
                    if (state.bookDetails.subjects.places.isNotEmpty()) {
                        SubjectSection(
                            title = "Places",
                            items = state.bookDetails.subjects.places
                        )
                    }

                    // People
                    if (state.bookDetails.subjects.people.isNotEmpty()) {
                        SubjectSection(
                            title = "Characters",
                            items = state.bookDetails.subjects.people
                        )
                    }

                    // Time Periods
                    if (state.bookDetails.subjects.times.isNotEmpty()) {
                        SubjectSection(
                            title = "Time Periods",
                            items = state.bookDetails.subjects.times
                        )
                    }
                }
            }

            is BookDetailsUiState.Error -> ErrorScreen(
                message = state.message,
                onRetryClick = viewModel::loadBookDetails
            )
        }
    }
}

@Composable
private fun SubjectSection(
    title: String,
    items: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = items.joinToString(", "),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}