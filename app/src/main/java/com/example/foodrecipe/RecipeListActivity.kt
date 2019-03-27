package com.example.foodrecipe

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import com.example.foodrecipe.models.Recipe
import com.example.foodrecipe.viewmodels.RecipeListViewModel

class RecipeListActivity : BaseActivity() {

    private lateinit var recipeListViewModel: RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        recipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel::class.java)

        subscribeObservers()
        testRequest()
    }

    private fun subscribeObservers(){
        recipeListViewModel.getRecipes().observe(this,
            Observer<MutableList<Recipe>> {recipes ->
                if (recipes != null) {
                    for(recipe: Recipe in recipes){
                        Log.d("TAG", "onChanged: ${recipe.title}")
                    }
                }
            })
    }

    fun searchRecipesApi(query: String, pageNumber: Int){
        recipeListViewModel.searchRecipesApi(query, pageNumber)
    }

    private fun testRequest(){
        searchRecipesApi("chicken", 1)
    }
}
