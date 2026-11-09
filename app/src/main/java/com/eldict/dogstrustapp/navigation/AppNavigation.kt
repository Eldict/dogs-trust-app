package com.eldict.dogstrustapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eldict.dogstrustapp.ui.screens.AdoptionInfoScreen
import com.eldict.dogstrustapp.ui.screens.DogDetailScreen
import com.eldict.dogstrustapp.ui.screens.DogListScreen
import com.eldict.dogstrustapp.ui.screens.HomeScreen
import com.eldict.dogstrustapp.viewmodel.DogViewModel
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val dogViewModel: DogViewModel = viewModel()

    // The route of the screen that is showing right now.
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val screenTitle = when (currentRoute) {
        Routes.DOG_LIST -> "Find a Dog"
        Routes.DOG_DETAIL -> "Dog Details"
        Routes.ADOPTION_INFO -> "Adoption Information"
        else -> "Dogs Trust"
    }
    val showBackArrow = currentRoute != Routes.HOME

    // The three main destinations shown in the bottom bar.
    val bottomBarItems = listOf(
        NavItem(label = "Home", icon = Icons.Default.Home, route = Routes.HOME),
        NavItem(label = "Dogs", icon = Icons.Default.Pets, route = Routes.DOG_LIST),
        NavItem(label = "Info", icon = Icons.Default.Info, route = Routes.ADOPTION_INFO)
    )
    // The bar is hidden on the dog details screen, which is not a main destination.
    val showBottomBar = bottomBarItems.any { item -> item.route == currentRoute }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = screenTitle) },
                navigationIcon = {
                    if (showBackArrow) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },

        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomBarItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                            label = { Text(text = item.label) }
                        )
                    }
                }
            }
        }



    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
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
                        onInterestedClick = { }
                    )
                }
            }
            composable(Routes.ADOPTION_INFO) {
                AdoptionInfoScreen()
            }
        }
    }
}