package com.rodrigoguerrero.recipes.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rodrigoguerrero.recipes.session.SessionHandler
import com.rodrigoguerrero.recipes.session.Validator
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val validator: Validator) : ViewModel() {

    var isPasswordValid: MutableLiveData<Boolean> = MutableLiveData()
    var isUsernameValid: MutableLiveData<Boolean> = MutableLiveData()

    fun validatePassword(password: String) {
        isPasswordValid.value = validator.isValidPassword(password)
    }

    fun validateUsername(username: String) {
        isUsernameValid.value = validator.isValidUsername(username)
    }

    fun login(username: String, context: Context) {
        SessionHandler.startSession(username, context)
    }
}