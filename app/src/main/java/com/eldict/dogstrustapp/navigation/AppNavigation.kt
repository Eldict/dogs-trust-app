package com.eldict.dogstrustapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eldict.dogstrustapp.ui.screens.AdoptionInfoScreen
import com.eldict.dogstrustapp.ui.screens.DogDetailScreen
import com.eldict.dogstrustapp.ui.screens.DogListScreen
import com.eldict.dogstrustapp.ui.screens.HomeScreen
import com.eldict.dogstrustapp.viewmodel.DogViewModel

// Holds the map of the whole app: which route shows which screen, and how the screens link together.
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    // The navController is the "driver": it moves between screens and remembers the back history.
    val navController = rememberNavController()
    // One ViewModel shared by the list and details screens.
    val dogViewModel: DogViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onFindDogClick = { navController.navigate(Routes.DOG_LIST) },
                onAdoptionInfoClick = { navController.navigate(Routes.ADOPTION_INFO) }
            )
        }
        composable(Routes.DOG_LIST) {
            DogListScreen(
                onDogClick = { dog -> navController.navigate(Routes.dogDetail(dog.id)) },
                dogViewModel = dogViewModel
            )
        }
        composable(
            route = Routes.DOG_DETAIL,
            arguments = listOf(navArgument("dogId") { type = NavType.IntType })
        ) { backStackEntry ->
            val dogId = backStackEntry.arguments?.getInt("dogId")
            val selectedDog = dogId?.let { dogViewModel.getDogById(it) }
            if (selectedDog != null) {
                DogDetailScreen(
                    dog = selectedDog,
                    onInterestedClick = { } // Req 11 will show a confirmation here.
                )
            }
        }
        composable(Routes.ADOPTION_INFO) {
            AdoptionInfoScreen()
        }
    }
}