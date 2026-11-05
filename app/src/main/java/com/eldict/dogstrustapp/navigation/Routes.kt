package com.eldict.dogstrustapp.navigation

// Holds the name (address) of every screen in one place, so a route is never mistyped.
object Routes {
    const val HOME = "home"
    const val DOG_LIST = "dogList"
    const val ADOPTION_INFO = "adoptionInfo"

    // The {dogId} part is a blank that gets filled with the real dog's id.
    const val DOG_DETAIL = "dogDetail/{dogId}"

    // Builds the real address for one dog, e.g. dogDetail(3) gives "dogDetail/3".
    fun dogDetail(dogId: Int): String = "dogDetail/$dogId"
}