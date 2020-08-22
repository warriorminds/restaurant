package com.rodrigoguerrero.recipes.repositories

import com.rodrigoguerrero.recipes.models.Recipe

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
}