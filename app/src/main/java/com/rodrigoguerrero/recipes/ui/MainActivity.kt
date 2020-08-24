package com.rodrigoguerrero.recipes.ui

import android.app.Activity
import android.content.Intent
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
import com.rodrigoguerrero.recipes.session.Session
import com.rodrigoguerrero.recipes.viewmodels.MainViewModel
import com.rodrigoguerrero.recipes.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        val LOGIN_REQUEST_CODE = 1000
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var session: Session

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
                    Snackbar.make(
                        binding.recipesRecyclerview,
                        getString(R.string.loading_error),
                        Snackbar.LENGTH_INDEFINITE
                    )
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

        viewModel.loginButtonState.observe(this, Observer {
            if (it) {
                binding.loginFab.text = getString(R.string.logout)
            } else {
                binding.loginFab.text = getString(R.string.login)
            }
        })
        viewModel.retrieveRecipes()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            viewModel.setLoginButtonState(true, this)
        }
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

        if (session.isSessionStarted(this)) {
            viewModel.setLoginButtonState(true, this)
        } else {
            viewModel.setLoginButtonState(false, this)
        }

        binding.loginFab.setOnClickListener {
            if (viewModel.loginButtonState.value == true) {
                viewModel.setLoginButtonState(false, this)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, LOGIN_REQUEST_CODE)
            }
        }
    }
}