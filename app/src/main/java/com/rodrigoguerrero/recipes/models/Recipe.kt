package com.rodrigoguerrero.recipes.models

import com.google.gson.annotations.SerializedName

data class Recipe(
    val calories: String,
    val carbos: String,
    val card: String,
    val country: String,
    @SerializedName("deliverable_ingredients")
    val deliverableIngredients: List<String>,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val favorites: Int,
    val fibers: String,
    val headline: String,
    val highlighted: Boolean,
    val id: String,
    @SerializedName("image")
    val imageUrl: String,
    val incompatibilities: String?,
    val ingredients: List<String>,
    val keywords: List<String>,
    val name: String,
    val products: List<String>,
    val proteins: String,
    val rating: Float?,
    val ratings: Int?,
    @SerializedName("thumb")
    val thumbnailUrl: String,
    @SerializedName("time")
    val preparationTime: String,
    @SerializedName("undeliverable_ingredients")
    val undeliverableIngredients: List<String>,
    val user: User,
    val weeks: List<String>
)