package com.example.pokemonapp.presentation.ui.composables.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapp.presentation.MainViewModel
import com.example.pokemonapp.presentation.ui.navigation.SetupNavGraph


@Composable
fun MainPage(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    Box {
        SetupNavGraph(navController = navController, mainViewModel)
        navController.navigate("PokemonListPage", navOptions = null)
    }
}