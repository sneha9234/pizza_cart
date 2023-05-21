package com.example.cartpizza.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.PizzaModel
import com.example.cartpizza.databinding.ItemPizzaBinding
import com.example.cartpizza.ui.interfaces.PizzaItemClickListeners

class PizzaViewHolder(private val binding: ItemPizzaBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pizzaModel: PizzaModel, listeners: PizzaItemClickListeners) {
        binding.pizza= pizzaModel
        binding.pizzaItemClick = listeners
    }

}