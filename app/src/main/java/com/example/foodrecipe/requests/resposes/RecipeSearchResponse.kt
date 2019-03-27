package com.example.foodrecipe.requests.resposes

import com.example.foodrecipe.models.Recipe
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(@SerializedName("count")
                                @Expose
                                val count: Int,
                                @SerializedName("recipes")
                                @Expose
                                val recipes: List<Recipe>)