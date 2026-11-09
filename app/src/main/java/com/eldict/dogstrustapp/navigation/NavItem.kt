package com.eldict.dogstrustapp.navigation


import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

// One item of the bottom bar - its text, its icon and the route it opens.
data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)