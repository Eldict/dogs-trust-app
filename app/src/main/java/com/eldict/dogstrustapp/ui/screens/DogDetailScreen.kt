package com.eldict.dogstrustapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eldict.dogstrustapp.model.Dog

// Shows the full profile of one dog.
// The dog to show is passed in, so this single screen works for every dog.
@Composable
fun DogDetailScreen(
    dog: Dog,
    onInterestedClick: () -> Unit,        // what happens when "I'm Interested" is pressed
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())   // lets the screen scroll if the text is long
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = dog.imageID),
            contentDescription = "Photo of ${dog.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = dog.name, style = MaterialTheme.typography.headlineMedium)
        Text(text = "${dog.breed} • ${dog.location}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AssistChip(onClick = {}, label = { Text(text = dog.gender.name) })
            AssistChip(onClick = {}, label = { Text(text = "${dog.age} years old") })
            AssistChip(onClick = {}, label = { Text(text = dog.size.name) })
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "About ${dog.name}", style = MaterialTheme.typography.titleMedium)
        Text(text = dog.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Suitable home", style = MaterialTheme.typography.titleMedium)
        Text(text = dog.suitableHomeInfo, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onInterestedClick, modifier = Modifier.fillMaxWidth()) {
            Text(text = "I'm Interested")
        }
    }
}