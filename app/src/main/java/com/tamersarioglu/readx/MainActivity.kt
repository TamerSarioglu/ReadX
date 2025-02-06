package com.tamersarioglu.readx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tamersarioglu.readx.presentation.books.components.TolkienTopAppBar
import com.tamersarioglu.readx.presentation.books.components.TolkienBottomBar
import com.tamersarioglu.readx.presentation.books.navigation.BooksNavigation
import com.tamersarioglu.readx.presentation.books.navigation.Screen
import com.tamersarioglu.readx.presentation.books.screen.AboutScreen
import com.tamersarioglu.readx.presentation.books.screen.AuthorsScreen
import com.tamersarioglu.readx.ui.theme.ReadXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReadXTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                var selectedTabIndex by remember { mutableIntStateOf(0) }

                Scaffold(
                    topBar = {
                        TolkienTopAppBar(
                            title = when {
                                currentRoute?.startsWith(Screen.BookDetail.route.substringBefore("{")) == true -> "Book Details"
                                else -> "Tolkien Books"
                            },
                            canNavigateBack = currentRoute != Screen.BooksList.route,
                            onNavigateBack = { navController.navigateUp() }
                        )
                    },
                    bottomBar = {
                        if (currentRoute == Screen.BooksList.route) {
                            TolkienBottomBar(
                                selectedTabIndex = selectedTabIndex,
                                onTabSelected = { selectedTabIndex = it }
                            )
                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        when {
                            currentRoute?.startsWith(Screen.BookDetail.route.substringBefore("{")) == true -> {
                                BooksNavigation(
                                    navController = navController,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            else -> {
                                when (selectedTabIndex) {
                                    0 -> BooksNavigation(
                                        navController = navController,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                    1 -> AuthorsScreen()
                                    2 -> AboutScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
