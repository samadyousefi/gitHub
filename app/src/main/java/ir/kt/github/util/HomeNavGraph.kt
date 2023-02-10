package ir.kt.github.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.kt.github.presentation.detail.DetailScreen
import ir.kt.github.presentation.favorite.FavoriteScreen
import ir.kt.github.presentation.home.HomeScreen

@Composable
fun HomeNavGraph(navHostController: NavHostController ) {
    NavHost(
        navController = navHostController,
        route = Graph.Home,
        startDestination = HomeDetailGraph.Home.route
    ) {
        composable(route = HomeDetailGraph.Home.route) {
            HomeScreen(navHostController)
        }
        composable(route = HomeDetailGraph.Detail.route+"/{username}") {
            DetailScreen(navHostController)
        }
        composable(route = HomeDetailGraph.Favorite.route) {

            FavoriteScreen(navHostController)
        }
    }
}

sealed class HomeDetailGraph(val route: String) {
    object Home : HomeDetailGraph("home")
    object Detail : HomeDetailGraph("detail")
    object Favorite : HomeDetailGraph("favorite")
}

object Graph {
    const val Home = "homeGraph"
}