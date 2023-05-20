package com.example.cartpizza.data

import androidx.room.withTransaction
import com.example.cartpizza.api.PizzaApi
import com.example.cartpizza.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class PizzaRepository @Inject constructor(
    private val api: PizzaApi,
    private val db: PizzaDatabase
) {
    private val pizzaDao = db.pizzaDao()

    fun getPizzas() = networkBoundResource(
        query = {
            pizzaDao.getAllPizzas()
        },
        fetch = {
            delay(2000)
            api.getPizzas()
        },
        saveFetchResult = { pizzas ->
            db.withTransaction {
                pizzaDao.deleteAllPizzas()
                pizzaDao.insertPizzas(pizzas)
            }
        }
    )
}