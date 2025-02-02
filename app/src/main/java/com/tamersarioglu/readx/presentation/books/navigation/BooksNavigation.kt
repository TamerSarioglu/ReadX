package com.tamersarioglu.readx.presentation.books.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tamersarioglu.readx.presentation.books.screen.BooksListScreen

sealed class Screen(val route: String) {
    data object BooksList : Screen("books_list")
    data object BookDetail : Screen("book_detail/{bookId}") {
        fun createRoute(bookId: String) = "book_detail/$bookId"
    }
}

@Composable
fun BooksNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BooksList.route,
        modifier = modifier
    ) {
        composable(Screen.BooksList.route) {
            BooksListScreen(
                onBookClick = { bookId ->
                    navController.navigate(Screen.BookDetail.createRoute(bookId))
                }
            )
        }

        composable(
            route = Screen.BookDetail.route,
            arguments = listOf(
                navArgument("bookId") { type = NavType.StringType }
            )
        ) {
            // BookDetailScreen implementation will go here
        }
    }
}
