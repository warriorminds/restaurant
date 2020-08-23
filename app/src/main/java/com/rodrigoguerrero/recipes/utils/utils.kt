package com.rodrigoguerrero.recipes.utils

fun String.getNutritionalInformation() = if (isEmpty()) {
    "Not available."
} else {
    this
}