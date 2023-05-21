package com.example.cartpizza.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cartpizza.R
import com.example.cartpizza.data.PizzaModel
import com.example.cartpizza.databinding.FragmentHomeBinding
import com.example.cartpizza.util.navigateSafely
import com.example.cartpizza.viewmodel.PizzaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), PizzaItemClickListeners {
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

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView() {
        binding.recyclerview.adapter = pizzaAdapter
        viewModel.pizzas.observe(viewLifecycleOwner) { result ->
            pizzaAdapter.setPizzas(listOf(result.data))
        }
        viewModel.cartItems.observe(viewLifecycleOwner) { result ->
            var sum = 0L
            result.forEach {
                sum += it.quantity?.times(it.price) ?: it.price
            }
            binding.txtTotalPrice.text = getString(R.string.total_order_amount, sum.toString())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onAddPizzaClicked(pizza: PizzaModel) {
        findNavController().navigateSafely(
            HomeFragmentDirections.actionHomeFragmentToCrustDialogFragment(pizza)
        )
    }

    override fun onRemovePizzaClicked(pizza: PizzaModel) {
        findNavController().navigateSafely(
            HomeFragmentDirections.actionHomeFragmentToCartDialogFragment(pizza)
        )
    }
}