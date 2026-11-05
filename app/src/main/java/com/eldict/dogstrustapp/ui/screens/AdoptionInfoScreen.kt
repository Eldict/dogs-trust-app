package com.eldict.dogstrustapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Explains how adopting a dog works, one Card per step.
@Composable
fun AdoptionInfoScreen(modifier: Modifier = Modifier) {

    // Each step is a pair: a title and a short explanation.
    val adoptionSteps = listOf(
        "1. Choose a dog" to "Browse the dogs and find one that fits your home and lifestyle.",
        "2. Say you're interested" to "Tell us which dog caught your eye and share some details about your home.",
        "3. Meet the dog" to "Visit the dog, spend time together and ask any questions you have.",
        "4. Home check" to "We make sure the dog will be safe, comfortable and happy in its new home.",
        "5. Take your dog home" to "Complete the paperwork and welcome your new best friend."
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "How adoption works", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "Adopting a dog is simple. Here are the steps from start to finish.",
            style = MaterialTheme.typography.bodyLarge
        )

        // One Card is drawn for every step, so there is no copy-pasted UI.
        adoptionSteps.forEach { (stepTitle, stepDescription) ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = stepTitle, style = MaterialTheme.typography.titleMedium)
                    Text(text = stepDescription, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}