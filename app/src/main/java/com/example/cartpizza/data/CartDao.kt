package com.example.cartpizza.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_table")
    fun getCartItems(): Flow<List<CartEntity>>

    @Insert
    suspend fun insertCartItem(cartItem: CartEntity)

    @Query("DELETE FROM cart_table WHERE item_id = :item_id")
    suspend fun deleteCartItem(item_id: Int)

    @Query("SELECT * from cart_table WHERE name= :name")
    fun getItemByName(name: String): CartEntity?

    @Query("SELECT quantity from cart_table WHERE item_id= :item_id")
    fun getItemQuantityById(item_id: Int): Long?

    @Query("UPDATE cart_table SET quantity = quantity + 1 WHERE name = :name")
    fun updateQuantity(name: String)

    @Query("UPDATE cart_table SET quantity = quantity - 1 WHERE item_id = :item_id")
    fun reduceQuantity(item_id: Int)

    suspend fun insertOrUpdateCartItem(cartItem: CartEntity){
        val itemFromDB: CartEntity? = getItemByName(cartItem.name)
        if (itemFromDB == null)
            insertCartItem(cartItem)
        else
            updateQuantity(cartItem.name)
    }

    suspend fun deleteOrUpdateCartItem(item_id: Int){
        val itemQuantityInDB: Long? = getItemQuantityById(item_id)
        if (itemQuantityInDB?.equals(1L) == true)
            deleteCartItem(item_id)
        else
            reduceQuantity(item_id)
    }

}
