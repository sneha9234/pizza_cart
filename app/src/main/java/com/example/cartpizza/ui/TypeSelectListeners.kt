package com.example.cartpizza.ui

import com.example.cartpizza.data.PizzaModel.Crusts
import com.example.cartpizza.data.PizzaModel.Crusts.Sizes

interface TypeSelectListeners {
    fun onCrustSelected(crusts: Crusts)
    fun onSizeSelected(size: Sizes)
}