package com.example.cartpizza.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.cartpizza.data.PizzaModel.Crusts
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


@ProvidedTypeConverter
class PizzaTypeConverters {
    private val gson = Gson()
    @TypeConverter
    fun fromString(value: String?): List<Crusts> {
        val listType: Type = object : TypeToken<ArrayList<Crusts?>?>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Crusts?>?): String {
        return gson.toJson(list)
    }
}