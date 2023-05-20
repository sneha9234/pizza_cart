package com.example.cartpizza.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaDao {

    @Query("SELECT * FROM pizza_table")
    fun getAllPizzas(): Flow<PizzaModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPizzas(pizzas: PizzaModel)

    @Query("DELETE FROM pizza_table")
    suspend fun deleteAllPizzas()

}