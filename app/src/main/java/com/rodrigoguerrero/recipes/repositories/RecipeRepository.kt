package com.rodrigoguerrero.recipes.repositories

import com.rodrigoguerrero.recipes.models.RecipeResponse
import retrofit2.Response

interface RecipeRepository {
    suspend fun getRecipes(): Response<RecipeResponse>
}