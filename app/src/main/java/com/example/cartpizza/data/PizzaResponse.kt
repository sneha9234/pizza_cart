package com.example.cartpizza.data

import com.google.gson.annotations.SerializedName

data class PizzaResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("isVeg")
    val isVeg: Boolean? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("defaultCrust")
    val defaultCrust: Int? = null,
    @SerializedName("crusts")
    val crusts: List<Crusts>? = null
)

data class Crusts(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("defaultSize")
    val defaultSize: Int? = null,
    @SerializedName("sizes")
    val sizes: List<Sizes>? = null
)

data class Sizes(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Int? = null
)