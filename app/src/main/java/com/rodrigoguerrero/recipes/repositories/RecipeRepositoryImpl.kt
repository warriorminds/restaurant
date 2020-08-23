package com.rodrigoguerrero.recipes.repositories

import com.rodrigoguerrero.recipes.models.RecipeApiState
import com.rodrigoguerrero.recipes.network.RecipesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipesService: RecipesService
) : RecipeRepository {
    override suspend fun getRecipes(): Flow<RecipeApiState> = flow {
        try {
            val response = recipesService.getRecipes()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    emit(RecipeApiState.Result(response.body()!!))
                } else {
                    emit(RecipeApiState.Result(listOf()))
                }
            } else {
                emit(RecipeApiState.Error)
            }
        } catch(e: Exception) {
            emit(RecipeApiState.Error)
        }
    }
}