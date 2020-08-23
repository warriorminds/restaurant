package com.rodrigoguerrero.recipes.repositories

import com.rodrigoguerrero.recipes.models.RecipeApiState
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(): Flow<RecipeApiState>
}