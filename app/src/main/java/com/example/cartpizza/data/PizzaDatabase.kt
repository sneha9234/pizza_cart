package com.example.cartpizza.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cartpizza.util.PizzaTypeConverters

@Database(entities = [PizzaModel::class], version = 1)
@TypeConverters(PizzaTypeConverters::class)
abstract class PizzaDatabase : RoomDatabase() {

    abstract fun pizzaDao(): PizzaDao
}