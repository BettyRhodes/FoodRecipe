package com.example.foodrecipe.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.foodrecipe.models.Recipe
import com.example.foodrecipe.requests.RecipeApiClient
import com.example.foodrecipe.viewmodels.RecipeListViewModel

class RecipeRepository private constructor() {
    private val recipeApiClient: RecipeApiClient = RecipeApiClient.getInstance()

    fun getRecipes(): LiveData<MutableList<Recipe>> = recipeApiClient.getRecipes()

    fun searchRecipesApi(query: String, pageNumber: Int){
        var page = pageNumber
        if(page == 0)
            page = 1
        recipeApiClient.searchRecipesApi(query, page)
    }

    companion object {
        private var instance: RecipeRepository? = null

        fun getInstance(): RecipeRepository {
            if (instance == null) {
                instance = RecipeRepository()
            }
            return instance as RecipeRepository
        }
    }
}