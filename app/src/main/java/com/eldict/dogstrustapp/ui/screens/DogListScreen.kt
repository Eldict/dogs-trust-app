package com.eldict.dogstrustapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eldict.dogstrustapp.model.Dog
import com.eldict.dogstrustapp.ui.components.DogCard
import com.eldict.dogstrustapp.viewmodel.DogViewModel


//shows every dog in a scorllable list. Using one reuseable DogCard per dog

@Composable
fun DogListScreen(
    onDogClick: (Dog) -> Unit,
    modifier: Modifier = Modifier,
    dogViewModel: DogViewModel = viewModel()
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dogViewModel.dogs) { dog ->
            DogCard(
                dog = dog,
                isFavourite = dog.id in dogViewModel.favouriteDogIds,
                onDogClick = onDogClick,
                onFavouriteClick = { dogViewModel.toggleFavourite(dog.id) }
            )
        }
    }
}