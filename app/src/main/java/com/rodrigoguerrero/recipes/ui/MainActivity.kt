package com.rodrigoguerrero.recipes.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rodrigoguerrero.recipes.R
import com.rodrigoguerrero.recipes.adapters.RecipeAdapter
import com.rodrigoguerrero.recipes.databinding.ActivityMainBinding
import com.rodrigoguerrero.recipes.models.RecipeApiState
import com.rodrigoguerrero.recipes.viewmodels.MainViewModel
import com.rodrigoguerrero.recipes.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private val recipesAdapter = RecipeAdapter()

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        viewModel.result.observe(this, Observer {
            when (it) {
                RecipeApiState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                RecipeApiState.Error -> {
                    binding.progress.visibility = View.GONE
                    Snackbar.make(binding.recipesRecyclerview, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
                        .setAction(
                            R.string.retry
                        ) {
                            viewModel.retrieveRecipes()
                        }
                        .show()
                }
                else -> {
                    binding.progress.visibility = View.GONE
                    recipesAdapter.addRecipes(it.recipesList)
                }
            }
        })
        viewModel.retrieveRecipes()
    }

    private fun init() {
        with(binding.recipesRecyclerview) {
            val gridlayoutManager = GridLayoutManager(
                this@MainActivity,
                resources.getInteger(R.integer.grid_size),
                GridLayoutManager.VERTICAL,
                false
            )
            layoutManager = gridlayoutManager
            adapter = recipesAdapter
        }
    }
}