package com.rodrigoguerrero.recipes.di

import com.rodrigoguerrero.recipes.RecipesApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, RecipesModule::class])
interface AppComponent {
    fun inject(apo: RecipesApplication)
}