package com.example.foodrecipe

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class App private constructor(){

    private val networkIO = Executors.newScheduledThreadPool(3)

    fun getNetworkIO(): ScheduledExecutorService = networkIO

    companion object {
        private var instance: App? = null

        fun getInstance(): App{
            if(instance == null){
                instance = App()
            }
            return instance as App
        }
    }
}