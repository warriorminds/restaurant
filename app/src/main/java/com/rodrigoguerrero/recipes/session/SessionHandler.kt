package com.rodrigoguerrero.recipes.session

import android.content.Context
import javax.inject.Inject

class SessionHandler @Inject constructor() : Session {

    private val LOGIN_PREFERENCES = "login_pref"
    private val USER_PREFERENCE = "user_pref"

    override fun startSession(username: String, context: Context) {
        val sharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(USER_PREFERENCE, username)
            apply()
        }
    }

    override fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(USER_PREFERENCE, "")
            apply()
        }
    }

    override fun isSessionStarted(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(USER_PREFERENCE, "")
        return username?.isNotEmpty() ?: false
    }

    override fun getUsername(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)
        return sharedPreferences.getString(USER_PREFERENCE, "")
    }
}