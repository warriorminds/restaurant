package com.rodrigoguerrero.recipes.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rodrigoguerrero.recipes.databinding.ActivityDetailsBinding
import com.rodrigoguerrero.recipes.models.Recipe
import com.rodrigoguerrero.recipes.session.SessionHandler
import com.rodrigoguerrero.recipes.utils.getNutritionalInformation
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    companion object {
        val RECIPE_EXTRA = "recipe_extra"
    }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

            if (SessionHandler.isSessionStarted(this@DetailsActivity)) {
                binding.fab.visibility = View.VISIBLE
            } else {
                binding.fab.visibility = View.GONE
            }

            title = ""
        }
    }
}