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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eldict.dogstrustapp.model.Dog

// One card showing a dog's photo and key details.
// Reused for every dog in the list, so there's no copy-pasted UI per dog.
@Composable
fun DogCard(
    dog: Dog,
    onDogClick: (Dog) -> Unit,   // what to do when tapped (navigation, in Req 6)
    modifier: Modifier = Modifier
) {
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
                contentDescription = "Photo of ${dog.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = dog.name, style = MaterialTheme.typography.titleLarge)
                Text(text = dog.breed, style = MaterialTheme.typography.bodyMedium)
                Text(text = "${dog.age} years old • ${dog.gender}")
                Text(text = dog.location)
            }
        }
    }
}