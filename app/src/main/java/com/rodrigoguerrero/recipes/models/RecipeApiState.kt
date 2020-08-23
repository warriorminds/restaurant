package com.rodrigoguerrero.recipes.models

sealed class RecipeApiState(val recipesList: List<Recipe>) {
    object Loading : RecipeApiState(listOf())
    object Error : RecipeApiState(listOf())
    data class Result(val result: List<Recipe>) : RecipeApiState(result)
}
