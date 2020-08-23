package com.rodrigoguerrero.recipes.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val email: String,
    @SerializedName("latlng")
    val latitudeLongitude: String,
    val name: String
) : Parcelable