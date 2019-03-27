package com.example.foodrecipe.requests

import com.example.foodrecipe.util.ConstantsBuffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(ConstantsBuffer.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = retrofitBuilder.build()

        val recipeApi = retrofit.create(RecipeApi::class.java)
    }
}