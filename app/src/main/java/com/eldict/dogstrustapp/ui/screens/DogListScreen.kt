package com.eldict.dogstrustapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eldict.dogstrustapp.model.Dog
import com.eldict.dogstrustapp.model.DogSize
import com.eldict.dogstrustapp.ui.components.DogCard
import com.eldict.dogstrustapp.viewmodel.DogViewModel

// Shows the dogs in a scrollable list, with a size filter row above it.
@Composable
fun DogListScreen(
    onDogClick: (Dog) -> Unit,
    modifier: Modifier = Modifier,
    dogViewModel: DogViewModel = viewModel()
) {
    Column(modifier = modifier.fillMaxSize()) {
        // The filter row: one button per option.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SizeFilterButton(
                label = "All",
                isActive = dogViewModel.selectedSize == null,
                onClick = { dogViewModel.selectSize(null) }
            )
            SizeFilterButton(
                label = "Small",
                isActive = dogViewModel.selectedSize == DogSize.SMALL,
                onClick = { dogViewModel.selectSize(DogSize.SMALL) }
            )
            SizeFilterButton(
                label = "Medium",
                isActive = dogViewModel.selectedSize == DogSize.MEDIUM,
                onClick = { dogViewModel.selectSize(DogSize.MEDIUM) }
            )
            SizeFilterButton(
                label = "Large",
                isActive = dogViewModel.selectedSize == DogSize.LARGE,
                onClick = { dogViewModel.selectSize(DogSize.LARGE) }
            )
        }

        // The list takes the space that is left under the filter row.
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dogViewModel.visibleDogs) { dog ->
                DogCard(
                    dog = dog,
                    isFavourite = dog.id in dogViewModel.favouriteDogIds,
                    onDogClick = onDogClick,
                    onFavouriteClick = { dogViewModel.toggleFavourite(dog.id) }
                )
            }
        }
    }
}

// One filter button. It is filled when it is the active filter, outlined otherwise.
@Composable
fun SizeFilterButton(label: String, isActive: Boolean, onClick: () -> Unit) {
    if (isActive) {
        Button(onClick = onClick) {
            Text(text = label)
        }
    } else {
        OutlinedButton(onClick = onClick) {
            Text(text = label)
        }
    }
}