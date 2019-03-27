package com.example.foodrecipe.requests

import com.example.foodrecipe.requests.resposes.RecipeResponse
import com.example.foodrecipe.requests.resposes.RecipeSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("api/search")
    fun searchRecipe(@Query("key") key: String,
                     @Query("q") query: String,
                     @Query("page") page: String): Call<RecipeSearchResponse>

    @GET("api/get")
    fun getRecipe(@Query("key") key: String,
                  @Query("rId") recipe_id: String): Call<RecipeResponse>
}