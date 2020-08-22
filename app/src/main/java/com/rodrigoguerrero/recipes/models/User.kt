package com.rodrigoguerrero.recipes.models

import com.google.gson.annotations.SerializedName

data class User(
    val email: String,
    @SerializedName("latlng")
    val latitudeLongitude: String,
    val name: String
)