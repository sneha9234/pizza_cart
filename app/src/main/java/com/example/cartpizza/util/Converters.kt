package com.example.cartpizza.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
//import com.example.cartpizza.data.PizzaModel.Crusts
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
//    @TypeConverter
//    fun toCrustJson(meaning: List<Crusts>) : String {
//        return jsonParser.toJson(
//            meaning,
//            object : TypeToken<ArrayList<Crusts>>(){}.type
//        ) ?: "[]"
//    }
//
//    @TypeConverter
//    fun fromCrustsJson(json: String): List<Crusts>{
//        return jsonParser.fromJson<ArrayList<Crusts>>(
//            json,
//            object: TypeToken<ArrayList<Crusts>>(){}.type
//        ) ?: emptyList()
//    }
}