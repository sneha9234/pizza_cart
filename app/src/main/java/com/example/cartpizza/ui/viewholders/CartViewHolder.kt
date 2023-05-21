package com.example.cartpizza.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.CartEntity
import com.example.cartpizza.databinding.ItemCartBinding
import com.example.cartpizza.ui.interfaces.CartItemClickListeners

class CartViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pizzaModel: CartEntity, listeners: CartItemClickListeners) {
        binding.cart= pizzaModel
        binding.cartItemClick = listeners
    }

}