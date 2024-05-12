package com.example.pokemonapp.presentation.ui.composables.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.util.DebugLogger
import com.example.pokemonapp.R
import com.example.pokemonapp.presentation.MainViewModel
import com.example.pokemonapp.presentation.ui.composables.PokemonCardItem

@Composable
fun PokemonListPage(navController: NavHostController, vm: MainViewModel){
    val loadingState = vm.isLoading.observeAsState()
    val pokemonList = vm.pokemonList.observeAsState()

    if (loadingState.value == true) {
        Box(modifier = Modifier
            .fillMaxSize()
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center)
            )
        }
    } else{
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(pokemonList.value!!) { item ->
                PokemonCardItem(navController = navController, item = item)
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        modifier = Modifier,
                        onClick = { vm.previousPage() }
                    ) {
                        Icon(modifier = Modifier,
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = null
                        )
                    }
                    IconButton(
                        modifier = Modifier,
                        onClick = { vm.nextPage() }
                    ) {
                        Icon(modifier = Modifier,
                            painter = painterResource(id = R.drawable.arrow_forward),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }

}