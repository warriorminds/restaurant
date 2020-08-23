package com.rodrigoguerrero.recipes.session

interface Validator {

    fun isValidUsername(username: String?): Boolean

    fun isValidPassword(password: String?): Boolean
}