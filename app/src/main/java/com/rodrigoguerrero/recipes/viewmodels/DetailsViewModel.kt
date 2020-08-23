package com.rodrigoguerrero.recipes.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodrigoguerrero.recipes.repositories.FavoritesRepository
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val favoritesRepository: FavoritesRepository) : ViewModel() {

    var isFavorite: MutableLiveData<Boolean> = MutableLiveData()

    fun isFavorite(recipeId: String, context: Context) {
        isFavorite.value = favoritesRepository.isFavorite(recipeId, context)
    }

    fun removeFromFavorites(recipeId: String, context: Context) {
        favoritesRepository.removeFromFavorites(recipeId, context)
        isFavorite.value = false
    }

    fun addToFavorites(recipeId: String, context: Context) {
        favoritesRepository.addToFavorites(recipeId, context)
        isFavorite.value = true
    }
}