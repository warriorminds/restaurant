package com.rodrigoguerrero.recipes

import android.app.Application
import com.rodrigoguerrero.recipes.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RecipesApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.create().also {
            it.inject(this)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}