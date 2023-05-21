package com.example.cartpizza.ui.interfaces

import com.example.cartpizza.data.PizzaModel

interface PizzaItemClickListeners {
    fun onAddPizzaClicked(pizza: PizzaModel)
    fun onRemovePizzaClicked(pizza: PizzaModel)
}