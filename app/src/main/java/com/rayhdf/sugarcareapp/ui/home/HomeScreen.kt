package com.rayhdf.sugarcareapp.ui.home

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rayhdf.sugarcareapp.ui.home.predict.PredictScreen
import com.rayhdf.sugarcareapp.ui.home.profile.ProfileScreen
import com.rayhdf.sugarcareapp.ui.home.track.TrackScreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = viewModel()) {

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
        ContentScreen(modifier = Modifier.padding(innerPadding).padding(16.dp), homeViewModel.selectedIndex)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int) {
    when (selectedIndex) {
        1 -> TrackScreen(modifier)
        2 -> PredictScreen(modifier)
        3 -> ProfileScreen(modifier)
    }

}