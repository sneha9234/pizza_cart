package com.example.cartpizza.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.PizzaModel.Crusts
import com.example.cartpizza.databinding.ItemCrustBinding

class CrustViewHolder(private val binding: ItemCrustBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(crusts: Crusts, listeners: TypeSelectListeners) {
        binding.crust= crusts
        binding.crustItemClick = listeners
    }

}