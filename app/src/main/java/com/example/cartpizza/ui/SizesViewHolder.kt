package com.example.cartpizza.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.PizzaModel.Crusts.Sizes
import com.example.cartpizza.databinding.ItemSizeBinding

class SizesViewHolder (private val binding: ItemSizeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(sizes: Sizes, listeners: TypeSelectListeners) {
        binding.size= sizes
        binding.sizeItemClick = listeners
    }

}