package com.eldict.dogstrustapp.data
import com.eldict.dogstrustapp.model.Dog
import com.eldict.dogstrustapp.model.DogSize
import com.eldict.dogstrustapp.model.DogGender
import com.eldict.dogstrustapp.R

object DogRepository {
    val dogs: List<Dog> = listOf(
        Dog(
            id = 1,
            name = "Pearl",
            breed = "French Bulldog",
            age = 6,
            gender = DogGender.FEMALE,
            size = DogSize.SMALL,
            location = "Dublin",
            description = "Adore belly rubs",
            suitableHomeInfo = "Quite",
            imageID = R.drawable.dog_pearl
        ),
        Dog(
            id = 2,
            name = "Alden",
            breed = "Irish Setter Cross",
            age = 9,
            gender = DogGender.MALE,
            size = DogSize.LARGE,
            location = "Dublin",
            description = "Like cuddles",
            suitableHomeInfo = "Able to handle dog fur sheds",
            imageID = R.drawable.dog_alden
        ),
        Dog(
            id = 3,
            name = "Walter",
            breed = "Pointer Cross",
            age = 8,
            gender = DogGender.MALE,
            size = DogSize.MEDIUM,
            location = "Dublin",
            description = "Like to go out often",
            suitableHomeInfo = "Friendly",
            imageID = R.drawable.dog_walter
        ),
        Dog(
            id = 4,
            name = "Benji",
            breed = "Collie Cross",
            age = 6,
            gender = DogGender.MALE,
            size = DogSize.LARGE,
            location = "Leinster",
            description = "Farm Doggo",
            suitableHomeInfo = "Kind hearted",
            imageID = R.drawable.dog_benji
        ),
        Dog(
            id = 5,
            name = "Neil",
            breed = "Whippet",
            age = 9,
            gender = DogGender.FEMALE,
            size = DogSize.MEDIUM,
            location = "Munster",
            description = "Likes walks",
            suitableHomeInfo = "Not for security",
            imageID = R.drawable.dog_nell
        ),
        Dog(
            id = 6,
            name = "Clement",
            breed = "Crossbreed",
            age = 3,
            gender = DogGender.MALE,
            size = DogSize.LARGE,
            location = "Leinster",
            description = "Likes belly rubs",
            suitableHomeInfo = "Child freindly",
            imageID = R.drawable.dog_clement
        ),
        Dog(
            id = 7,
            name = "Daisy",
            breed = "Jack Russell",
            age = 2,
            gender = DogGender.FEMALE,
            size = DogSize.SMALL,
            location = "Cork",
            description = "Happiest and playful in the group",
            suitableHomeInfo = "Would prefer attention",
            imageID = R.drawable.dog_daisy
        ),
        Dog(
            id = 8,
            name = "Bruno",
            breed = "Labrador Cross",
            age = 4,
            gender = DogGender.MALE,
            size = DogSize.LARGE,
            location = "Galway",
            description = "Likes belly rubs",
            suitableHomeInfo = "Neat and tidy home",
            imageID = R.drawable.dog_bruno
        ),
    )
}