package com.example.cartpizza.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.CartEntity
import com.example.cartpizza.databinding.ItemCartBinding
import com.example.cartpizza.ui.interfaces.CartItemClickListeners
import com.example.cartpizza.ui.viewholders.CartViewHolder

class CartAdapter (private val listener: CartItemClickListeners): RecyclerView.Adapter<CartViewHolder>() {
    private var cartItems = mutableListOf<CartEntity?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setCartItems(cartItems: List<CartEntity?>) {

        this.cartItems = cartItems.toMutableList()
        notifyDataSetChanged()
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): CartViewHolder =
        CartViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.apply {
            cartItems[position]?.let { bind(it, listener) }
        }

    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}