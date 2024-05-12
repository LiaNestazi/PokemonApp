package com.example.pokemonapp.presentation.ui.composables.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokemonapp.R
import com.example.pokemonapp.presentation.MainViewModel

@Composable
fun PokemonDetailsPage(navController: NavHostController, vm: MainViewModel, pokemonName: String?){
    if (pokemonName == null){
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            Text(text = "Can't load info")
        }

    } else{
        vm.getPokemonDetails(pokemonName)
        val pokemonState = vm.currentPokemon.observeAsState()


        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){

                IconButton(
                    modifier = Modifier,
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(modifier = Modifier,
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = null
                    )
                }
                Text(modifier = Modifier,
                    text = pokemonState.value?.name.toString().replaceFirstChar { it.uppercaseChar() },
                    style = MaterialTheme.typography.titleLarge)
            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                Column (modifier = Modifier
                    .padding(16.dp)
                ){
                    Text(modifier = Modifier.fillMaxWidth(),
                        text = "Details",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center)
                    Text(text = "Base experience: ${pokemonState.value?.base_experience.toString()}")
                    Text(text = "Height: ${pokemonState.value?.height.toString()}")
                    Text(text = "Weight: ${pokemonState.value?.weight.toString()}")
                }
            }
            if (pokemonState.value?.abilities != null){
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    ) {
                        Text(modifier = Modifier.fillMaxWidth(),
                            text = "Abilities",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center)
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            items(pokemonState.value?.abilities!!){
                                Column {
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Text(text = "Name: ${it.ability?.name}")
                                    Text(text = "Slot: ${it.slot}")
                                    Text(text = "Is hidden: ${it.is_hidden}")
                                }
                            }
                        }
                    }
                }
            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(modifier = Modifier.fillMaxWidth(),
                        text = "Forms",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center)
                    pokemonState.value?.forms?.forEach {
                        Column {
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = "Name: ${it.name}")
                        }
                    }
                }

            }
            if (pokemonState.value?.stats != null){
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    ) {
                        Text(modifier = Modifier.fillMaxWidth(),
                            text = "Stats",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center)
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            items(pokemonState.value?.stats!!){
                                Column {
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Text(text = "Name: ${it.stat?.name}")
                                    Text(text = "Is battle only: ${it.stat?.is_battle_only}")
                                    Text(text = "Base stat: ${it.base_stat}")
                                    Text(text = "Effort: ${it.effort}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}