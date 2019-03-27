package com.example.foodrecipe.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.foodrecipe.models.Recipe
import com.example.foodrecipe.repositories.RecipeRepository

class RecipeListViewModel: ViewModel() {
    private var recipeRepository: RecipeRepository = RecipeRepository.getInstance()

    fun getRecipes(): LiveData<MutableList<Recipe>> = recipeRepository.getRecipes()

    fun searchRecipesApi(query: String, pageNumber: Int){
        var page = pageNumber
        if(page == 0)
            page = 1
        recipeRepository.searchRecipesApi(query, page)
    }
}