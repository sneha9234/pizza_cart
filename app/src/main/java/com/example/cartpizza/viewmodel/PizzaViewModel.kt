package com.example.cartpizza.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cartpizza.data.PizzaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PizzaViewModel @Inject constructor(
    repository: PizzaRepository
) : ViewModel() {
    val pizzas = repository.getPizzas().asLiveData(Dispatchers.IO)
    val cartItems = repository.getCartItems().asLiveData()
}