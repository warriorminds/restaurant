package com.rodrigoguerrero.recipes.repositories

import android.content.Context
import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rodrigoguerrero.recipes.session.Session
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class FavoritesRepositoryTest {

    private lateinit var context: Context
    private lateinit var preferences: SharedPreferences
    private lateinit var repository: FavoritesRepositoryImpl
    @Mock
    private lateinit var session: Session

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        context = InstrumentationRegistry.getInstrumentation().context
        preferences = context.getSharedPreferences("favorites_prefs", Context.MODE_PRIVATE)
        repository = FavoritesRepositoryImpl(session)

        `when`(session.getUsername(context)).thenReturn("username")
    }

    @After
    fun cleanup() {
        preferences.edit().clear().commit()
    }

    @Test
    fun addRecipesToFavoritesTest() {
        repository.addToFavorites("123", context)
        repository.addToFavorites("456", context)
        assertTrue(preferences.getStringSet("username", HashSet())!!.contains("123"))
        assertTrue(preferences.getStringSet("username", HashSet())!!.contains("456"))
    }

    @Test
    fun removeRecipesFromFavoritesTest() {
        repository.addToFavorites("123", context)
        repository.addToFavorites("456", context)
        assertTrue(preferences.getStringSet("username", HashSet())!!.contains("123"))
        assertTrue(preferences.getStringSet("username", HashSet())!!.contains("456"))

        repository.removeFromFavorites("123", context)
        assertFalse(preferences.getStringSet("username", HashSet())!!.contains("123"))

        repository.removeFromFavorites("456", context)
        assertFalse(preferences.getStringSet("username", HashSet())!!.contains("456"))
    }

    @Test
    fun isRecipeFavoriteTest() {
        repository.addToFavorites("123", context)
        assertTrue(repository.isFavorite("123", context))
        assertFalse(repository.isFavorite("456", context))

        repository.addToFavorites("456", context)
        assertTrue(repository.isFavorite("123", context))
        assertTrue(repository.isFavorite("456", context))

        repository.removeFromFavorites("456", context)
        assertTrue(repository.isFavorite("123", context))
        assertFalse(repository.isFavorite("456", context))
    }
}