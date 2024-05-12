package com.example.pokemonapp.presentation.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.example.domain.models.Pokemon
import okhttp3.OkHttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit


@Composable
fun PokemonCardItem(navController: NavHostController, item: Pokemon) {

    val imageLoader = LocalContext.current.imageLoader.newBuilder()
        .logger(DebugLogger())
        .build()

    Card(
        modifier = Modifier
            .height(64.dp)
            .clickable {
                navController.navigate("PokemonDetailsPage/" + item.name)
            },
    ) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp),
                imageLoader = imageLoader,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image_url)
                    .setHeader("User-Agent", "Mozilla/5.0")
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                text = item.name.toString().replaceFirstChar { it.uppercaseChar() },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}