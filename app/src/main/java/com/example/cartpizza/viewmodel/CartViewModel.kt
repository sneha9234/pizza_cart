package com.example.cartpizza.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cartpizza.data.CartEntity
import com.example.cartpizza.data.PizzaModel
import com.example.cartpizza.data.PizzaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: PizzaRepository
) : ViewModel() {

    var selectedCrustName: String = ""
    var selectedSizeName: String = ""
    var selectedPizzaName: String = ""
    var selectedSizePrice: Long = 0

    val cartItems =
        repository.getCartItems().asLiveData()

    fun insertItem() = viewModelScope.launch(Dispatchers.IO) {
        val cartItem = CartEntity(item_id = null, name = "$selectedCrustName $selectedSizeName $selectedPizzaName", price = selectedSizePrice)
        repository.insertCartItem(cartItem)
    }

    fun deleteItem(cartItem: CartEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCartItem(cartItem)
    }

    fun increasePizzaQuantity(pizzaItem: PizzaModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.increasePizzaQuantity(pizzaItem)
    }

    fun lessenPizzaQuantity(pizzaItem: PizzaModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.decreasePizzaQuantity(pizzaItem)
    }
}