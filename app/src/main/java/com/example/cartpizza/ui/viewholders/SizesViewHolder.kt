package com.example.cartpizza.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.PizzaModel.Crusts.Sizes
import com.example.cartpizza.databinding.ItemSizeBinding
import com.example.cartpizza.ui.interfaces.TypeSelectListeners

class SizesViewHolder (private val binding: ItemSizeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(sizes: Sizes, listeners: TypeSelectListeners) {
        binding.size= sizes
        binding.sizeItemClick = listeners
    }

}