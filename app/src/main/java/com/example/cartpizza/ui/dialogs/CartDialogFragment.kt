package com.example.cartpizza.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cartpizza.R
import com.example.cartpizza.data.CartEntity
import com.example.cartpizza.databinding.FragmentCartDialogBinding
import com.example.cartpizza.ui.adapters.CartAdapter
import com.example.cartpizza.ui.interfaces.CartItemClickListeners
import com.example.cartpizza.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartDialogFragment : DialogFragment(), CartItemClickListeners {
    private var _binding: FragmentCartDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModels()
    private val cartAdapter = CartAdapter(this)
    private val args: CartDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        binding.rvCartItems.adapter = cartAdapter
        viewModel.cartItems.observe(viewLifecycleOwner) { result ->
            cartAdapter.setCartItems(result)
            var priceSum = 0L
            var quantitySum = 0L
            result.forEach {
                priceSum += it.quantity?.times(it.price) ?: it.price
                quantitySum += it.quantity?:0
            }
            binding.txtTotal.text = getString(R.string.total_items, quantitySum.toString(), priceSum.toString())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onCartItemRemoved(cartItem: CartEntity) {
        viewModel.deleteItem(cartItem)
        viewModel.lessenPizzaQuantity(args.pizzaInfo)
    }
}