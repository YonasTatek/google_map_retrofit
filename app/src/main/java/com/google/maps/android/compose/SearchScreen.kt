package com.google.maps.android.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.maps.android.compose.model.Location


@OptIn(ExperimentalMaterialApi::class)
@Composable

fun SearchScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val mapL = mainViewModel.movieListResponse
    val value = mainViewModel.countryValue
    Scaffold(
        modifier = Modifier
            .fillMaxSize().background(Color.Gray),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 10.dp, end = 10.dp
                    )
            ) {
                IconButton(onClick = { mainViewModel.getMapList() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }

                TextField(

                    modifier = Modifier.fillMaxWidth(),
                    value = value,
                    label = { Text(text = "Enter Location") },
                    placeholder = { Text(text = "Enter your location") },
                    onValueChange = { mainViewModel.setCountry(it) },
                    trailingIcon = {
                        IconButton(onClick = { mainViewModel.getMapList() }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,

                                )
                        }

                    }
                )

            }
        }

    ) {

        LazyColumn(modifier = Modifier.padding(it).padding(horizontal = 15.dp)) {
            items(mapL) { it ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 20.dp).height(80.dp), elevation = 5.dp,
                    onClick = {
                        mainViewModel.setLocationState(Location(lat = it.lat, lon = it.lon))
                        navHostController.navigate("map")


                    }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(30.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.baseline_location_on_24),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(horizontalAlignment = Alignment.Start) {


                            if (it.display_name.split(',').size > 1)
                                Text(text = it.display_name.split(',')[1],
                                style = MaterialTheme.typography.body1
                                    )
                            Text(
                                text = it.display_name.split(',')[0],
                                style = MaterialTheme.typography.subtitle1
                            )


                        }


                    }
                }
            }
        }

    }


}