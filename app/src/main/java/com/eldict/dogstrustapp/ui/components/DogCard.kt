package com.eldict.dogstrustapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eldict.dogstrustapp.model.Dog
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// One card for one dog. It is reused for every dog in the list.
@Composable
fun DogCard(
    dog: Dog,
    isFavourite: Boolean,
    onDogClick: (Dog) -> Unit,
    onFavouriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Becomes true once, when the card first appears. Makes the card fade in.
    var cardVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        cardVisible = true
    }

    AnimatedVisibility(visible = cardVisible) {
        Card(
            onClick = { onDogClick(dog) },
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = dog.imageID),
                    contentDescription = dog.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(96.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = dog.name, style = MaterialTheme.typography.titleLarge)
                    Text(text = dog.breed, style = MaterialTheme.typography.bodyMedium)
                    Text(text = "${dog.age} years old • ${dog.gender}")
                    Text(text = dog.location)
                }

                val heartColor by animateColorAsState(
                    targetValue = if (isFavourite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    animationSpec = tween(durationMillis = 500)
                )

                // Heart size: grows when the dog is a favourite.
                val heartSize by animateDpAsState(
                    targetValue = if (isFavourite) 32.dp else 24.dp,
                    animationSpec = tween(durationMillis = 300)
                )

                // The heart. Tapping it changes the favourite state, not the card.
                IconButton(onClick = onFavouriteClick) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favourite",
                        tint = heartColor,
                        modifier = Modifier.size(heartSize)
                    )
                }
            }
        }
    }
}