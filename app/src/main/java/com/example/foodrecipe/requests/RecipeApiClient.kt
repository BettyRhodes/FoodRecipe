package com.example.foodrecipe.requests

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.foodrecipe.App
import com.example.foodrecipe.models.Recipe
import com.example.foodrecipe.requests.resposes.RecipeSearchResponse
import com.example.foodrecipe.util.ConstantsBuffer
import com.example.foodrecipe.util.ConstantsBuffer.Companion.NETWORK_TIMEOUT
import retrofit2.Call
import java.io.IOException
import java.util.concurrent.TimeUnit

class RecipeApiClient private constructor(){

    private val recipes: MutableLiveData<MutableList<Recipe>> = MutableLiveData()
    private var retrieveRecipesRunnable: RetrieveRecipesRunnable? = null

    fun getRecipes(): LiveData<MutableList<Recipe>> = recipes

    fun searchRecipesApi(query: String, pageNumber: Int){
        if(retrieveRecipesRunnable != null)
            retrieveRecipesRunnable = null

        retrieveRecipesRunnable = RetrieveRecipesRunnable(query, pageNumber)

        val handler = App.getInstance().getNetworkIO().submit(retrieveRecipesRunnable)

        App.getInstance().getNetworkIO().schedule(Runnable {
            handler.cancel(true)
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    }

    private inner class RetrieveRecipesRunnable internal constructor(private val query: String, private val pageNumber: Int):
        Runnable{

        private var cancelRequest: Boolean = false

        override fun run() {
            try{
                val response = getRecipes(query, pageNumber).execute()

                if(cancelRequest){
                    return
                }
                if(response.code() == 200){
                    val list = ArrayList((response.body() as RecipeSearchResponse).recipes)
                    if(pageNumber == 1)
                        recipes.postValue(list)
                    else{
                        val currentRecipes: MutableList<Recipe> = recipes.value as MutableList<Recipe>
                        currentRecipes.addAll(list)
                    }
                } else{
                    val error = response.errorBody()?.string()
                    Log.e("TAG", "run: $error")
                    recipes.postValue(null)
                }
            }catch (e: IOException){
                e.printStackTrace()
            }

        }

        private fun getRecipes(query: String, pageNumber: Int): Call<RecipeSearchResponse> {
            return ServiceGenerator.recipeApi.searchRecipe(
                ConstantsBuffer.API_KEY,
                query,
                pageNumber.toString()
            )
        }

        private fun cancelRequest(){
            Log.d("TAG", "cancelRequest: canceling the search request")
            cancelRequest = true
        }

    }

    companion object {
        private var instance: RecipeApiClient? = null

         fun getInstance(): RecipeApiClient{
            if(instance == null){
                instance = RecipeApiClient()
            }
            return instance as RecipeApiClient
        }
    }
}