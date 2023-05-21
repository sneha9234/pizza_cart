package com.example.cartpizza.api

import com.example.cartpizza.data.PizzaModel
import retrofit2.http.GET

interface PizzaApi {

    companion object {
        const val BASE_URL = "https://625bbd9d50128c570206e502.mockapi.io/api/"
    }

    @GET("v1/pizza/1")
    suspend fun getPizzas(): PizzaModel
}