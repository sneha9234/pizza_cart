package com.example.cartpizza.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pizza_table")
data class PizzaModel(
    @PrimaryKey val id: String,
    val name: String,
    val isVeg: Boolean,
    val description: String,
    val defaultCrust: Int,
   // val crusts: List<Crusts>
)
//{
//    data class Crusts(
//        val id: Int,
//        val name: String,
//        val defaultSize: Int,
//        //val sizes: List<Sizes>
//    )
//    {
//        data class Sizes(
//            val id: Int,
//            val name: String,
//            val price: Int
//        )
//    }
//}