package com.rodrigoguerrero.recipes.session

import android.content.Context

interface Session {
    fun startSession(username: String, context: Context)

    fun logout(context: Context)

    fun isSessionStarted(context: Context): Boolean

    fun getUsername(context: Context): String?
}