package com.example.cartpizza.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.PizzaModel
import com.example.cartpizza.databinding.ItemPizzaBinding

class PizzaViewHolder(private val binding: ItemPizzaBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: PizzaModel, listeners: ItemClickListeners) {
        binding.pizza= movie
        binding.productItemClick = listeners
    }


}