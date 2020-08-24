package com.rodrigoguerrero.recipes.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.rodrigoguerrero.recipes.databinding.ActivityDetailsBinding
import com.rodrigoguerrero.recipes.models.Recipe
import com.rodrigoguerrero.recipes.session.Session
import com.rodrigoguerrero.recipes.utils.getNutritionalInformation
import com.rodrigoguerrero.recipes.viewmodels.DetailsViewModel
import com.rodrigoguerrero.recipes.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    companion object {
        val RECIPE_EXTRA = "recipe_extra"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var sessionHandler: Session

    private val viewModel: DetailsViewModel by viewModels {
        viewModelFactory
    }
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.isFavorite.observe(this, Observer {
            if (it) {
                binding.recipeDetails.favoriteImage.visibility = View.VISIBLE
            } else {
                binding.recipeDetails.favoriteImage.visibility = View.GONE
            }
        })
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun init() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            val recipe = intent.extras?.get(RECIPE_EXTRA) as Recipe

            Picasso.get().load(recipe.imageUrl).into(recipeImage)

            binding.recipeDetails.recipeName.text = recipe.name
            binding.recipeDetails.recipeHeadline.text = recipe.headline
            binding.recipeDetails.recipeTime.text = "${recipe.preparationTime.subSequence(2..3)} minutes"
            binding.recipeDetails.recipeDescription.text = recipe.description
            binding.recipeDetails.recipeIngredients.text = recipe.ingredients.joinToString("\n")
            binding.recipeDetails.nutritionCaloriesValue.text = recipe.calories.getNutritionalInformation()
            binding.recipeDetails.nutritionCarbsValue.text = recipe.carbos.getNutritionalInformation()
            binding.recipeDetails.nutritionFatsValue.text = recipe.fats.getNutritionalInformation()
            binding.recipeDetails.nutritionProteinsValue.text = recipe.proteins.getNutritionalInformation()
            binding.fab.setOnClickListener {
                if (viewModel.isFavorite.value == true) {
                    viewModel.removeFromFavorites(recipe.id, this@DetailsActivity)
                } else {
                    viewModel.addToFavorites(recipe.id, this@DetailsActivity)
                }
            }

            if (sessionHandler.isSessionStarted(this@DetailsActivity)) {
                binding.fab.visibility = View.VISIBLE
            } else {
                binding.fab.visibility = View.GONE
            }

            viewModel.isFavorite(recipe.id, this@DetailsActivity)
            title = ""
        }
    }
}