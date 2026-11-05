package com.eldict.dogstrustapp.model

//Represents a dog available for adoption; holds only data

// The two possible genders of a dog. An enum only allows these exact values.
enum class DogGender {
    MALE, FEMALE
}

// The three possible sizes of a dog.
enum class DogSize {
    SMALL, MEDIUM, LARGE
}

// Represents one dog available for adoption; it only holds data, no screen code.
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
    val imageID: Int
)