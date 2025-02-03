package com.tamersarioglu.readx.presentation.books.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tamersarioglu.readx.presentation.books.screen.BookDetailScreen
import com.tamersarioglu.readx.presentation.books.screen.BooksListScreen

sealed class Screen(val route: String) {
    data object BooksList : Screen("books_list")
    data object BookDetail : Screen("book_detail/{workId}") {
        fun createRoute(workId: String) = "book_detail/$workId"
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
                    val cleanId = bookId.replace("/", "").removePrefix("OL")
                    navController.navigate(Screen.BookDetail.createRoute(cleanId))
                }
            )
        }

        composable(
            route = Screen.BookDetail.route,
            arguments = listOf(
                navArgument("workId") { type = NavType.StringType }
            )
        ) {
            BookDetailScreen()
        }
    }
}
