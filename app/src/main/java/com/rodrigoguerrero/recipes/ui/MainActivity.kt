package com.rodrigoguerrero.recipes.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.rodrigoguerrero.recipes.adapters.RecipeAdapter
import com.rodrigoguerrero.recipes.databinding.ActivityMainBinding
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
        viewModel.recipes.observe(this, Observer {
            recipesAdapter.addRecipes(it)
        })
    }

    private fun init() {
        with(binding.recipesRecyclerview) {
            val gridlayoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
            layoutManager = gridlayoutManager
            adapter = recipesAdapter
        }
    }
}