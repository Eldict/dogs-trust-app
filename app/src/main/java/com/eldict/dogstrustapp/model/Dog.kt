package com.eldict.dogstrustapp.model

//Represents a dog available for adoption; holds only data
enum class DogGender {
    MALE, FEMALE
}

enum class DogSize{
    SMALL,MEDIUM,LARGE
}

data class Dog(
    val id: Int,
    val name: String,
    val breed: String,
    val age: Int,
    val gender: DogGender,
    val size: DogSize,
    val location: String,
    val description: String,
    val suitableHomeInfo: String,
    val imageResId: Int
)