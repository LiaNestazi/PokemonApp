package com.example.pokemonapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokemonapp.presentation.MainViewModel
import com.example.pokemonapp.presentation.ui.composables.pages.PokemonDetailsPage
import com.example.pokemonapp.presentation.ui.composables.pages.PokemonListPage

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {

    NavHost(navController = navController, startDestination = "PokemonListPage") {
        composable("PokemonListPage") {
            PokemonListPage(navController = navController, vm = mainViewModel)
        }
        composable(
            route = "PokemonDetailsPage/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                },
            )
        ){
            PokemonDetailsPage(
                navController = navController,
                vm = mainViewModel,
                pokemonName = it.arguments?.getString("name")
            )
        }
    }
}