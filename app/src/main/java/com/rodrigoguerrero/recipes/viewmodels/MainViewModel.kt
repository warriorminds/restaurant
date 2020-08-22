package com.rodrigoguerrero.recipes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rodrigoguerrero.recipes.models.Recipe
import com.rodrigoguerrero.recipes.repositories.RecipeRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val recipesRepository: RecipeRepository
) : ViewModel() {

    val recipes: LiveData<List<Recipe>> = liveData(Dispatchers.IO) {
        emit(recipesRepository.getRecipes())
    }
}