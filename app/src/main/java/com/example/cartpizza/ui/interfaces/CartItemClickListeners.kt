package com.example.cartpizza.ui.interfaces

import com.example.cartpizza.data.CartEntity

interface CartItemClickListeners {
    fun onCartItemRemoved(cartItem: CartEntity)
}