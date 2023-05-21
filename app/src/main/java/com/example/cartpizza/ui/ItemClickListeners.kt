package com.example.cartpizza.ui

import com.example.cartpizza.data.PizzaModel

interface ItemClickListeners {
    fun onAddPizzaClicked(pizza: PizzaModel)
    fun onRemovePizzaClicked(pizza: PizzaModel)
}