package com.example.cartpizza.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_table")
    fun getCartItems(): Flow<CartEntity>

    @Insert
    suspend fun insertCartItem(cartItem: CartEntity)

    @Query("DELETE FROM cart_table WHERE item_id = :item_id")
    suspend fun deleteCartItem(item_id: Int)
}