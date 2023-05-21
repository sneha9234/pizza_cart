package com.example.cartpizza.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cartpizza.data.PizzaModel
import com.example.cartpizza.databinding.FragmentSelectCrustDialogBinding
import com.example.cartpizza.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectCrustDialogFragment: DialogFragment(), TypeSelectListeners {
    private var _binding: FragmentSelectCrustDialogBinding? = null
    private val binding get() = _binding!!
    private val args: SelectCrustDialogFragmentArgs by navArgs()
    private val crustAdapter = CrustAdapter(this)
    private val sizeAdapter = SizeAdapter(this)
    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSelectCrustDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
    }

    private fun initView() {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        binding.rvCrusts.adapter = crustAdapter
        binding.rvSizes.adapter = sizeAdapter
        viewModel.selectedPizzaName = args.pizzaInfo.name
        args.pizzaInfo.crusts.forEach {
            it.isSelected = it.id ==  args.pizzaInfo.defaultCrust
        }
        crustAdapter.setCrusts(args.pizzaInfo.crusts)
        val defCrust = args.pizzaInfo.crusts.filter { it.id == args.pizzaInfo.defaultCrust }
        viewModel.selectedCrustName = defCrust[0].name
        defCrust[0].sizes.forEach {
            if(it.id ==  defCrust[0].defaultSize){
                it.isSelected = true
                viewModel.selectedSizeName = it.name
                viewModel.selectedSizePrice = it.price
            }
            else{
                it.isSelected = false
            }
        }
        sizeAdapter.setSizes(defCrust[0].sizes)
    }

    private fun initListeners() {
        binding.btnAddToCart.setOnClickListener {
            viewModel.insertItem()
            viewModel.increasePizzaQuantity(args.pizzaInfo)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onCrustSelected(crusts: PizzaModel.Crusts) {
        args.pizzaInfo.crusts.forEach {
            crusts.isSelected = it ==  crusts
        }
        crustAdapter.setCrusts(args.pizzaInfo.crusts)
        viewModel.selectedCrustName = crusts.name

        crusts.sizes.forEach {
            if(it.id ==  crusts.defaultSize){
                it.isSelected = true
                viewModel.selectedSizeName = it.name
                viewModel.selectedSizePrice = it.price
            }
            else{
                it.isSelected = false
            }
        }
        sizeAdapter.setSizes(crusts.sizes)
    }

    override fun onSizeSelected(size: PizzaModel.Crusts.Sizes) {
        viewModel.selectedSizeName = size.name
        viewModel.selectedSizePrice = size.price
    }
}