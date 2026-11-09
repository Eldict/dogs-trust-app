package com.eldict.dogstrustapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.eldict.dogstrustapp.data.DogRepository
import com.eldict.dogstrustapp.model.Dog
import com.eldict.dogstrustapp.model.DogSize

// Sits between the data and the screens. It keeps the favourites and the size filter.
class DogViewModel : ViewModel() {
    val dogs: List<Dog> = DogRepository.dogs

    // The ids of the dogs the user has marked as favourite.
    var favouriteDogIds by mutableStateOf(setOf<Int>())
        private set

    // The size the user picked. Null means "show all sizes".
    var selectedSize by mutableStateOf<DogSize?>(null)
        private set

    // The dogs that match the current filter.
    val visibleDogs: List<Dog>
        get() = if (selectedSize == null) dogs else dogs.filter { dog -> dog.size == selectedSize }

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

    // Sets the size filter. Pass null to show every size.
    fun selectSize(size: DogSize?) {
        selectedSize = size
    }
}