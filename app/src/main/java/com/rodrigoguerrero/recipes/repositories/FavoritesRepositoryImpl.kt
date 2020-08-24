package com.rodrigoguerrero.recipes.repositories

import android.content.Context
import com.rodrigoguerrero.recipes.session.Session
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val sessionHandler: Session) : FavoritesRepository {

    companion object {
        private val FAVORITES_PREFS = "favorites_prefs"
    }

    override fun addToFavorites(recipeId: String, context: Context) {
        val sharedPreferences = context.getSharedPreferences(FAVORITES_PREFS, Context.MODE_PRIVATE)
        val username = sessionHandler.getUsername(context)
        val userFavorites = sharedPreferences.getStringSet(username, HashSet<String>())

        userFavorites?.add(recipeId)

        with(sharedPreferences.edit()) {
            putStringSet(username, userFavorites)
            apply()
        }
    }

    override fun removeFromFavorites(recipeId: String, context: Context) {
        val sharedPreferences = context.getSharedPreferences(FAVORITES_PREFS, Context.MODE_PRIVATE)
        val username = sessionHandler.getUsername(context)
        val userFavorites = sharedPreferences.getStringSet(username, HashSet<String>())

        userFavorites?.remove(recipeId)

        with(sharedPreferences.edit()) {
            putStringSet(username, userFavorites)
            apply()
        }
    }

    override fun isFavorite(recipeId: String, context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(FAVORITES_PREFS, Context.MODE_PRIVATE)
        val username = sessionHandler.getUsername(context)
        val userFavorites = sharedPreferences.getStringSet(username, HashSet<String>())

        return userFavorites?.contains(recipeId) ?: false
    }
}