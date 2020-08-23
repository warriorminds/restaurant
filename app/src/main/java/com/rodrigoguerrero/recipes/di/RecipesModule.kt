package com.rodrigoguerrero.recipes.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodrigoguerrero.recipes.network.RecipesService
import com.rodrigoguerrero.recipes.repositories.FavoritesRepository
import com.rodrigoguerrero.recipes.repositories.FavoritesRepositoryImpl
import com.rodrigoguerrero.recipes.repositories.RecipeRepository
import com.rodrigoguerrero.recipes.repositories.RecipeRepositoryImpl
import com.rodrigoguerrero.recipes.session.Validator
import com.rodrigoguerrero.recipes.session.ValidatorImpl
import com.rodrigoguerrero.recipes.ui.DetailsActivity
import com.rodrigoguerrero.recipes.ui.LoginActivity
import com.rodrigoguerrero.recipes.ui.MainActivity
import com.rodrigoguerrero.recipes.viewmodels.DetailsViewModel
import com.rodrigoguerrero.recipes.viewmodels.LoginViewModel
import com.rodrigoguerrero.recipes.viewmodels.MainViewModel
import com.rodrigoguerrero.recipes.viewmodels.ViewModelFactory
import com.rodrigoguerrero.recipes.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class RecipesModule {

    @Module
    companion object {
        @Provides
        fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY })
            .build()

        @Provides
        fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

        @Provides
        fun providesRetrofitService(retrofit: Retrofit): RecipesService = retrofit.create(RecipesService::class.java)
    }

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun detailsActivity(): DetailsActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(bindMainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindRecipeRepository(recipeRepositoryImpl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    abstract fun bindFavoritesRepository(favoritesRepository: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    abstract fun bindValidator(validatorImpl: ValidatorImpl): Validator
}