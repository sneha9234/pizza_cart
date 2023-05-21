package com.example.cartpizza.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pizza_table")
data class PizzaModel(
    @PrimaryKey(autoGenerate = false) val id: String,
    var name: String,
    var isVeg: Boolean,
    var description: String,
    val defaultCrust: Int,
    val crusts: List<Crusts>,
    //local variable
    var quantity: Int
) {
    data class Crusts(
        val id: Int,
        val name: String,
        val defaultSize: Int,
        val sizes: List<Sizes>
    ) {
        data class Sizes(
            val id: Int,
            val name: String,
            val price: Int
        )
    }
}