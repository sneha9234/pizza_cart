package com.example.cartpizza.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.RawValue
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "pizza_table")
data class PizzaModel(
    @PrimaryKey(autoGenerate = false) val id: String,
    var name: String,
    var isVeg: Boolean,
    var description: String,
    val defaultCrust: Int,
    val crusts: @RawValue List<Crusts>,
    //local variable
    var quantity: Int = 0
) : Parcelable {
    data class Crusts(
        val id: Int,
        val name: String,
        val defaultSize: Int,
        val sizes: List<Sizes>,
        //local variable
        var isSelected: Boolean
    ) {
        data class Sizes(
            val id: Int,
            val name: String,
            val price: Long,
            //local variable
            var isSelected: Boolean
        )
    }
}