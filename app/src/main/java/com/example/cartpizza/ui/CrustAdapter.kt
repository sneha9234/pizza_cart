package com.example.cartpizza.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.PizzaModel.Crusts
import com.example.cartpizza.databinding.ItemCrustBinding

class CrustAdapter(private val listener: TypeSelectListeners): RecyclerView.Adapter<CrustViewHolder>() {
    private var cartItems = mutableListOf<Crusts?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setCrusts(cartItems: List<Crusts?>) {
        this.cartItems = cartItems.toMutableList()
        notifyDataSetChanged()
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): CrustViewHolder =
        CrustViewHolder(ItemCrustBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CrustViewHolder, position: Int) {
        holder.apply {
            cartItems[position]?.let { bind(it, listener) }
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}