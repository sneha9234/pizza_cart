package com.example.cartpizza.data

import androidx.room.withTransaction
import com.example.cartpizza.api.PizzaApi
import com.example.cartpizza.util.networkBoundResource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PizzaRepository @Inject constructor(
    private val api: PizzaApi,
    private val db: PizzaDatabase
) {
    private val pizzaDao = db.pizzaDao()
    private val cartDao = db.cartDao()

    fun getPizzas() = networkBoundResource(
        query = {
            pizzaDao.getAllPizzas()
        },
        fetch = {
            api.getPizzas()
        },
        saveFetchResult = { pizzas ->
            db.withTransaction {
                pizzaDao.deleteAllPizzas()
                pizzaDao.insertPizzas(pizzas)
            }
        }
    )

     fun getCartItems(): Flow<List<CartEntity>> {
        return cartDao.getCartItems()
    }

    suspend fun insertCartItem(cartEntity: CartEntity){
        cartDao.insertOrUpdateCartItem(cartEntity)
    }

    suspend fun deleteCartItem(cartEntity: CartEntity){
        cartEntity.item_id?.let { cartDao.deleteOrUpdateCartItem(it) }
    }

    suspend fun increasePizzaQuantity(pizzaModel: PizzaModel){
        pizzaDao.increasePizzaQuantity(pizzaModel.id)
    }

    suspend fun decreasePizzaQuantity(pizzaModel: PizzaModel){
        pizzaDao.decreasePizzaQuantity(pizzaModel.id)
    }

}