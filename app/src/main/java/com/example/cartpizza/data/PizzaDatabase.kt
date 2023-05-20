package com.example.cartpizza.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cartpizza.util.Converters

//@TypeConverters(Converters::class)
@Database(entities = [PizzaModel::class], version = 1)
abstract class PizzaDatabase : RoomDatabase() {

    abstract fun pizzaDao(): PizzaDao
}