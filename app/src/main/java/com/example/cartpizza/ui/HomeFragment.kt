package com.example.cartpizza.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cartpizza.data.PizzaModel
import com.example.cartpizza.databinding.FragmentHomeBinding
import com.example.cartpizza.viewmodel.PizzaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListeners {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PizzaViewModel by viewModels()
    private val pizzaAdapter = PizzaAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.recyclerview.adapter = pizzaAdapter
        viewModel.pizzas.observe(viewLifecycleOwner) { result ->
            pizzaAdapter.setPizzas(listOf(result.data))
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onAddPizzaClicked(pizza: PizzaModel) {
        Toast.makeText(context, pizza.id+" added", Toast.LENGTH_SHORT).show()
    }

    override fun onRemovePizzaClicked(pizza: PizzaModel) {
        Toast.makeText(context, pizza.id+" removed", Toast.LENGTH_SHORT).show()
    }
}