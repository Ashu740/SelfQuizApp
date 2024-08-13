package com.example.flashcardquiz

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/home") {
        composable(route = "/home"){
            Home(navController)
        }
        composable(route = "/score/{totalScore}",
            arguments = listOf(navArgument(
                name = "totalScore",
                builder = {type = StringType}
            ))
        ){backStackEntry ->
            Score(total = backStackEntry.arguments?.getString("totalScore").toString()
                ,navController = navController)
        }
        composable(route = "/quiz/{category}",
            arguments = listOf(navArgument(
                    name = "category",
                    builder = { type = StringType }
                ),)
        ) { backStackEntry ->
            QuizScreen(
                category = backStackEntry.arguments?.getString("category").toString(),
                navController = navController
            )
        }
        composable(route = "/add/{category}",
            arguments = listOf(
                navArgument(
                    name = "category",
                    builder = {type = StringType}
                )
            )
        ){backStackEntry ->
            AddScreen(category = backStackEntry.arguments?.getString("category").toString(),
                navController = navController)
        }
    }

}