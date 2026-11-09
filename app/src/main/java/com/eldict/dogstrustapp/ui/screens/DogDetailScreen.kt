package com.eldict.dogstrustapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eldict.dogstrustapp.model.Dog
import com.eldict.dogstrustapp.model.DogGender
import com.eldict.dogstrustapp.model.DogSize

// Shows the full profile of one dog.
// The dog to show is passed in, so this single screen works for every dog.
@Composable
fun DogDetailScreen(
    dog: Dog,
    onInterestedClick: () -> Unit,        // what happens when "I'm Interested" is pressed
    modifier: Modifier = Modifier
) {
    // Whether the suitable home section is open. Starts closed.
    var showSuitableHome by remember { mutableStateOf(false) }

    // Becomes true once, when the screen first appears. Starts the photo and details animation.
    var photoOpened by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        photoOpened = true
    }

    // Whether the "I'm Interested" confirmation dialog is showing.
    var showInterestDialog by remember { mutableStateOf(false) }

    // Photo height: small at first, then grows to full size when photoOpened turns true.
    val photoHeight by animateDpAsState(
        targetValue = if (photoOpened) 280.dp else 100.dp,
        animationSpec = tween(durationMillis = 700)
    )

    if (showInterestDialog) {
        AlertDialog(
            onDismissRequest = { showInterestDialog = false },
            title = { Text(text = "Thank you") },
            text = { Text(text = "Your interest in ${dog.name} has been noted. The Dogs Trust team will be in touch.") },
            confirmButton = {
                TextButton(onClick = { showInterestDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

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
                .height(photoHeight)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Name, chips and text appear after the photo starts growing.
        AnimatedVisibility(visible = photoOpened) {
            Column {
                Text(text = dog.name, style = MaterialTheme.typography.headlineMedium)
                Text(text = "${dog.breed} • ${dog.location}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Enum names are all capitals, so each value is turned into readable text.
                    val genderText = if (dog.gender == DogGender.MALE) "Male" else "Female"
                    val sizeText = when (dog.size) {
                        DogSize.SMALL -> "Small"
                        DogSize.MEDIUM -> "Medium"
                        DogSize.LARGE -> "Large"
                    }
                    AssistChip(onClick = {}, label = { Text(text = "Gender: $genderText") })
                    AssistChip(onClick = {}, label = { Text(text = "Age: ${dog.age}") })
                    AssistChip(onClick = {}, label = { Text(text = "Size: $sizeText") })
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "About ${dog.name}", style = MaterialTheme.typography.titleMedium)
                Text(text = dog.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { showSuitableHome = !showSuitableHome }) {
                    Text(text = if (showSuitableHome) "Hide suitable home" else "Show suitable home")
                }
                AnimatedVisibility(visible = showSuitableHome) {
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = dog.suitableHomeInfo, style = MaterialTheme.typography.bodyLarge)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = { showInterestDialog = true; onInterestedClick() }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "I'm Interested")
                }
            }
        }
    }
}