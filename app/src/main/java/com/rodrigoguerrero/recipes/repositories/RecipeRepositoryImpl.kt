package com.rodrigoguerrero.recipes.repositories

import com.rodrigoguerrero.recipes.models.Recipe
import com.rodrigoguerrero.recipes.network.RecipesService
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipesService: RecipesService
) : RecipeRepository {
    override suspend fun getRecipes(): List<Recipe> {
        val response = recipesService.getRecipes()
        return if (response.isSuccessful) {
            if (response.body() != null) {
                response.body()!!
            } else {
                listOf()
            }
        } else {
            listOf()
        }
    }
}