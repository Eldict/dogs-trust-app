package com.eldict.dogstrustapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.eldict.dogstrustapp.data.DogRepository
import com.eldict.dogstrustapp.model.Dog

// Sits between the data and the screens. It also keeps the favourite dog ids.
class DogViewModel : ViewModel() {
    val dogs: List<Dog> = DogRepository.dogs

    // The ids of the dogs the user has marked as favourite.
    var favouriteDogIds by mutableStateOf(setOf<Int>())
        private set

    fun getDogById(dogId: Int): Dog? {
        return dogs.find { dog -> dog.id == dogId }
    }

    // Adds the dog if it is not a favourite yet, otherwise removes it.
    fun toggleFavourite(dogId: Int) {
        favouriteDogIds = if (dogId in favouriteDogIds) {
            favouriteDogIds - dogId
        } else {
            favouriteDogIds + dogId
        }
    }
}