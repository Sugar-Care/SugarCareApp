package com.rayhdf.sugarcareapp.ui.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rayhdf.sugarcareapp.ui.home.first.FirstScreen
import com.rayhdf.sugarcareapp.ui.home.predict.PredictInputScreen
import com.rayhdf.sugarcareapp.ui.home.predict.PredictInputViewModel
import com.rayhdf.sugarcareapp.ui.home.predict.PredictScreen
import com.rayhdf.sugarcareapp.ui.home.profile.ProfileScreen
import com.rayhdf.sugarcareapp.ui.home.track.TrackScreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = viewModel()) {

    val navController = rememberNavController()
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Track", Icons.Default.DateRange),
        NavItem("Predict", Icons.Default.KeyboardArrowUp),
        NavItem("Profile", Icons.Default.Person)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = homeViewModel.selectedIndex == index,
                        onClick = {
                            homeViewModel.selectedIndex = index
                            navController.navigate(navItem.label)
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(16.dp)) {
            NavHost(
                navController = navController,
                startDestination = "Home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("Home") { FirstScreen(modifier) }
                composable("Track") { TrackScreen(modifier) }
                composable("Predict") { PredictScreen(modifier, navController) }
                composable("Profile") { ProfileScreen(modifier) }
                composable("PredictInput") { PredictInputScreen(viewModel = remember { PredictInputViewModel() }) }
            }
        }
    }
}