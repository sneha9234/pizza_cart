package com.example.cartpizza.ui

import com.example.cartpizza.data.CartEntity

interface CartItemClickListeners {
    fun onCartItemRemoved(cartItem: CartEntity)
}