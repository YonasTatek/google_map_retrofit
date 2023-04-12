package com.google.maps.android.compose

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.maps.android.compose.model.Location


@OptIn(ExperimentalMaterialApi::class)
@Composable

fun SearchScreen(navHostController: NavHostController,mainViewModel: MainViewModel= viewModel(),modifier: Modifier = Modifier) {

    val mapL = mainViewModel.movieListResponse
    val value = mainViewModel.countryValue

    Column(modifier = modifier) {
        Row() {
            TextField(value = value, onValueChange = { mainViewModel.setCountry(it) })
            Button(onClick = { mainViewModel.getMapList() }) {
                Text(text = "Search")
            }
        }


        LazyColumn {
            items(mapL) { it ->
                Card(modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        mainViewModel.setLocationState(Location(lat = it.lat, lon = it.lon))
                        navHostController.navigate("map")


                    }) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = it.display_name.split(',')[0])

                    }
                }
            }
        }
    }


}