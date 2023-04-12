package com.google.maps.android.compose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.maps.android.compose.data.ApiService
import com.google.maps.android.compose.model.Location
import com.google.maps.android.compose.model.MapDataItem
import kotlinx.coroutines.launch



class MainViewModel : ViewModel() {
    var movieListResponse:List<MapDataItem> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    var countryValue:String by mutableStateOf("")
    var LocationUiState:Location by mutableStateOf(
        Location(
            lat = "10",
            lon = "10"
        )
    )

    fun setLocationState(lo: Location){
        LocationUiState = lo
    }

    fun setCountry(country1:String){
        countryValue = country1
    }

    fun getMapList() {

        viewModelScope.launch {

            val apiService = ApiService.getInstance()
            try {
                val movieList = apiService.getMap(country = countryValue)
                movieListResponse = movieList
            }
            catch (e: Exception) {
                Log.d("viewModelMessage error",movieListResponse.toString())
                errorMessage = e.message.toString()
            }
            Log.d("viewModelMessage",movieListResponse.toString())
        }
    }

}