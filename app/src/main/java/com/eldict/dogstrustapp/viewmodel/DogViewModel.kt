package com.eldict.dogstrustapp.viewmodel

import androidx.lifecycle.ViewModel
import com.eldict.dogstrustapp.data.DogRepository
import com.eldict.dogstrustapp.model.Dog

//sits between data and screen
class DogViewModel : ViewModel() {
    val dogs: List<Dog> = DogRepository.dogs

    fun getDogById(dogId: Int): Dog? {
        return dogs.find{dog -> dog.id == dogId}
    }

}

