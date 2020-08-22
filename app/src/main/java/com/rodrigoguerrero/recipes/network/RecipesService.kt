package com.rodrigoguerrero.recipes.network

import com.rodrigoguerrero.recipes.models.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipesService {
    @GET("/hellofreshdevtests/warriorminds-android-test/master/recipes.json?token=ACVXPTHUTPVT4A3XPXUNIYS7JLC2S")
    suspend fun getRecipes(): Response<RecipeResponse>
}