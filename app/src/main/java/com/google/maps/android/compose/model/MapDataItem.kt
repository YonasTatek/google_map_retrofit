package com.google.maps.android.compose.model

data class MapDataItem(
    val boundingbox: List<String>,
    val `class`: String,
    val display_name: String,
    val icon: String,
    val importance: Double,
    val lat: String,
    val licence: String,
    val lon: String,
    val osm_id: Long,
    val osm_type: String,
    val place_id: Int,
    val type: String
)

data class Location(
    val lat: String = "10",
    val lon: String = "10",
)
