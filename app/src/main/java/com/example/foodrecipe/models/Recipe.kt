package com.example.foodrecipe.models

import com.google.gson.annotations.SerializedName

data class Recipe(@SerializedName("publisher") val publisher: String,
                  @SerializedName("title") val title: String,
                  @SerializedName("ingredients") val ingredients: List<String>,
                  @SerializedName("recipe_id") val recipe_id: String,
                  @SerializedName("image_url") val image_url: String,
                  @SerializedName("social_rank") val social_rank: Float)