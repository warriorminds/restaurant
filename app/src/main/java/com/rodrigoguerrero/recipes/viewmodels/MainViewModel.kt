package com.rodrigoguerrero.recipes.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.recipes.models.RecipeApiState
import com.rodrigoguerrero.recipes.repositories.RecipeRepository
import com.rodrigoguerrero.recipes.session.Session
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val recipesRepository: RecipeRepository,
    private val session: Session
) : ViewModel() {

    var result: MutableLiveData<RecipeApiState> = MutableLiveData()
    var loginButtonState: MutableLiveData<Boolean> = MutableLiveData()

    fun retrieveRecipes() {
        result.value = RecipeApiState.Loading
        viewModelScope.launch {
            recipesRepository.getRecipes().collect {
                result.value = it
            }
        }
    }

    fun setLoginButtonState(isLoggedIn: Boolean, context: Context) {
        loginButtonState.value = isLoggedIn
        if (!isLoggedIn) {
            session.logout(context)
        }
    }
}