package com.example.cartpizza.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.cartpizza.data.PizzaModel
import com.example.cartpizza.databinding.ItemPizzaBinding

class PizzaAdapter(val listener: ItemClickListeners): RecyclerView.Adapter<PizzaViewHolder>() {
    private var pizzas = mutableListOf<PizzaModel?>()

    @JvmName("setPizzas1")
    @SuppressLint("NotifyDataSetChanged")
    fun setPizzas(pizzas: List<PizzaModel?>) {

        this.pizzas = pizzas.toMutableList()
        notifyDataSetChanged()
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): PizzaViewHolder =
        PizzaViewHolder(ItemPizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        holder.apply {
            pizzas[position]?.let { bind(it, listener) }
        }

    }

    override fun getItemCount(): Int {
        return pizzas.size
    }

}