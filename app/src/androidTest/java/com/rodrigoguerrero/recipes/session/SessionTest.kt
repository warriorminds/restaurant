package com.rodrigoguerrero.recipes.session

import android.content.Context
import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SessionTest {

    private lateinit var context: Context
    private lateinit var preferences: SharedPreferences
    private lateinit var session: SessionHandler

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        preferences = context.getSharedPreferences("login_pref", Context.MODE_PRIVATE)
        session = SessionHandler()
    }

    @After
    fun cleanup() {
        preferences.edit().clear().commit()
    }

    @Test
    fun startSessionTest() {
        session.startSession("username", context)
        assertEquals("username", preferences.getString("user_pref", ""))
    }

    @Test
    fun logoutTest() {
        session.startSession("username", context)
        assertEquals("username", preferences.getString("user_pref", ""))

        session.logout(context)
        assertEquals("", preferences.getString("user_pref", "empty"))
    }

    @Test
    fun isSessionStartedTest() {
        assertFalse(session.isSessionStarted(context))
        session.startSession("username", context)
        assertTrue(session.isSessionStarted(context))
    }

    @Test
    fun getUsernameTest() {
        assertEquals("", session.getUsername(context))
        session.startSession("username", context)
        assertEquals("username", session.getUsername(context))
    }
}