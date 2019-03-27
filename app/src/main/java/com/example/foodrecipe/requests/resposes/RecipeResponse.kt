package com.example.foodrecipe.requests.resposes

import com.example.foodrecipe.models.Recipe
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeResponse(@SerializedName("recipe")
                          @Expose
                          val recipe: Recipe)