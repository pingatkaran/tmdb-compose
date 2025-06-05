package com.app.tmdb_compose.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.tmdb_compose.R
import com.app.tmdb_compose.domain.model.ContentItem
import com.app.tmdb_compose.presentation.ui.theme.TextColorSecondary

@Composable
fun MoviePosterItem(
    movie: ContentItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(150.dp) // Typical width for posters in a row
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Placeholder for Image - In real app, use Coil/Glide/Picasso
            if (movie.imageUrl != null) {
                // Basic Image composable, replace with an async image loader
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with Coil/Glide
                    contentDescription = movie.title,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.Crop,
                    // For network images, you'd use an image loading library here.
                    // Example with a placeholder color if imageUrl is for some reason not loadable by a library
                    //  .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray)
                )
                // Fallback if painterResource fails or for Coil's placeholder:
                // AsyncImage(model = movie.imageUrl, contentDescription = movie.title, modifier = ..., placeholder = painterResource(id=R.drawable.placeholder_image))

            } else {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.Movie,
                        contentDescription = movie.title,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = movie.description.take(30) + "...", // Short description
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                maxLines = 2,
                color = TextColorSecondary
            )
        }
    }
}
