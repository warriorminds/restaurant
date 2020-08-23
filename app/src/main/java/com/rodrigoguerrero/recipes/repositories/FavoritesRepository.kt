package com.rodrigoguerrero.recipes.repositories

import android.content.Context

interface FavoritesRepository {

    fun addToFavorites(recipeId: String, context: Context)

    fun isFavorite(recipeId: String, context: Context): Boolean

    fun removeFromFavorites(recipeId: String, context: Context)
}