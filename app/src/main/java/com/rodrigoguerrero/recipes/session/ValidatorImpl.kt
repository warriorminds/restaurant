package com.rodrigoguerrero.recipes.session

import javax.inject.Inject

class ValidatorImpl @Inject constructor() : Validator {

    override fun isValidUsername(username: String?) =
        !username.isNullOrEmpty() && username.contains('@') && username.contains(".com")

    override fun isValidPassword(password: String?) =
        !password.isNullOrEmpty() && password.length >= 8
}