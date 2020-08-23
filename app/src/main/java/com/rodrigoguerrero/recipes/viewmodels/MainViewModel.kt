package com.rodrigoguerrero.recipes.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.recipes.models.RecipeApiState
import com.rodrigoguerrero.recipes.repositories.RecipeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val recipesRepository: RecipeRepository
) : ViewModel() {

    var result: MutableLiveData<RecipeApiState> = MutableLiveData()

    fun retrieveRecipes() {
        result.value = RecipeApiState.Loading
        viewModelScope.launch {
            recipesRepository.getRecipes().collect {
                result.value = it
            }
        }
    }
}