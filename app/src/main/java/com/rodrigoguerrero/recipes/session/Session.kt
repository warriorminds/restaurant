package com.rodrigoguerrero.recipes.session

import android.content.Context

class SessionHandler {

    companion object {
        private val LOGIN_PREFERENCES = "login_pref"
        private val USER_PREFERENCE = "user_pref"

        fun startSession(username: String, context: Context) {
            val sharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putString(USER_PREFERENCE, username)
                apply()
            }
        }

        fun logout(context: Context) {
            val sharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putString(USER_PREFERENCE, "")
                apply()
            }
        }

        fun isSessionStarted(context: Context): Boolean {
            val sharedPreferences = context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)
            val username = sharedPreferences.getString(USER_PREFERENCE, "")
            return username?.isNotEmpty() ?: false
        }
    }
}