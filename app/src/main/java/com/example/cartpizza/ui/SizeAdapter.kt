package com.example.cartpizza.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.PizzaModel.Crusts.Sizes
import com.example.cartpizza.databinding.ItemSizeBinding

class SizeAdapter(private val listener: TypeSelectListeners): RecyclerView.Adapter<SizesViewHolder>() {
    private var cartItems = mutableListOf<Sizes?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setSizes(cartItems: List<Sizes?>) {
        this.cartItems = cartItems.toMutableList()
        notifyDataSetChanged()
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): SizesViewHolder =
        SizesViewHolder(ItemSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SizesViewHolder, position: Int) {
        holder.apply {
            cartItems[position]?.let { bind(it, listener) }
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}