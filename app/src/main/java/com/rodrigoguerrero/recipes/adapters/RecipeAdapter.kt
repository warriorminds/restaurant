package com.rodrigoguerrero.recipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodrigoguerrero.recipes.databinding.RecipeItemBinding
import com.rodrigoguerrero.recipes.models.Recipe
import com.squareup.picasso.Picasso

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private val recipes: MutableList<Recipe> = mutableListOf()

    inner class ViewHolder(private val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            with (binding) {
                Picasso.get().load(recipe.imageUrl).into(recipeImage)
                recipeName.text = recipe.name
                recipeHeadline.text = recipe.headline
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    fun addRecipes(recipes: List<Recipe>) {
        this.recipes.addAll(recipes)
        notifyDataSetChanged()
    }
}