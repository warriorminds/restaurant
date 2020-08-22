package com.rodrigoguerrero.recipes.repositories

import com.rodrigoguerrero.recipes.network.RecipesService
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipesService: RecipesService
) : RecipeRepository {
    override suspend fun getRecipes() = recipesService.getRecipes()
}