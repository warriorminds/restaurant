package com.rodrigoguerrero.recipes.session

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ValidatorTest {

    private lateinit var validatorImpl: ValidatorImpl

    @Before
    fun setup() {
        validatorImpl = ValidatorImpl()
    }

    @Test
    fun `validate correct email returns true`() {
        assertTrue(validatorImpl.isValidUsername("asdfa@asdfa.com"))
    }

    @Test
    fun `validate incorrect email returns false`() {
        assertFalse(validatorImpl.isValidUsername("asdfaasdfa.com"))
        assertFalse(validatorImpl.isValidUsername("asdfaas@dfa.co"))
        assertFalse(validatorImpl.isValidUsername(""))
        assertFalse(validatorImpl.isValidUsername(null))
    }

    @Test
    fun `validate correct password returns true`() {
        assertTrue(validatorImpl.isValidPassword("12345678"))
    }

    @Test
    fun `validate incorrect password returns false`() {
        assertFalse(validatorImpl.isValidPassword("asdf"))
        assertFalse(validatorImpl.isValidPassword(""))
        assertFalse(validatorImpl.isValidPassword(null))
    }
}