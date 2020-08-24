package com.rodrigoguerrero.recipes.repositories

import com.google.gson.Gson
import com.rodrigoguerrero.recipes.models.RecipeApiState
import com.rodrigoguerrero.recipes.models.RecipeResponse
import com.rodrigoguerrero.recipes.network.RecipesService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.io.File

class RecipesRepositoryTest {

    @Mock
    lateinit var service: RecipesService
    lateinit var repositoryImpl: RecipeRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repositoryImpl = RecipeRepositoryImpl(service)
    }

    @Test
    fun `when response is returned from the service then list of recipes is returned`() {
        val response =
            Gson().fromJson(getJson("json/response.json", this.javaClass.classLoader!!), RecipeResponse::class.java)
        runBlocking {
            `when`(service.getRecipes()).thenReturn(Response.success(200, response))
            repositoryImpl.getRecipes().collect {
                assertNotNull(it.recipesList)
                assertEquals(9, it.recipesList.size)
            }
        }
    }

    @Test
    fun `when response is error from the service then error is returned`() {
        runBlocking {
            `when`(service.getRecipes()).thenReturn(Response.error(404, ResponseBody.create(null, "")))
            repositoryImpl.getRecipes().collect {
                assertTrue(it is RecipeApiState.Error)
                assertNotNull(it.recipesList)
                assertEquals(0, it.recipesList.size)
            }
        }
    }

    private fun getJson(jsonFile: String, classLoader: ClassLoader): String {
        val uri = classLoader.getResource(jsonFile)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}