package com.example.cartpizza.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class CartEntity(
    @PrimaryKey(autoGenerate = true) val item_id: Int,
    var quantity: Int,
    var name: String,
    var price: Long
)