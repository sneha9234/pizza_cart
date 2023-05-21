package com.example.cartpizza.ui.dialogs

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
import com.example.cartpizza.ui.adapters.CrustAdapter
import com.example.cartpizza.ui.adapters.SizeAdapter
import com.example.cartpizza.ui.interfaces.TypeSelectListeners
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

        viewModel.crusts = args.pizzaInfo.crusts
        viewModel.crusts?.forEach {crustItems->
            if(crustItems.id == args.pizzaInfo.defaultCrust){
                crustItems.isSelected = true
                viewModel.selectedCrustName = crustItems.name
                crustItems.sizes.forEach { sizeItems->
                    if(sizeItems.id == crustItems.defaultSize){
                        sizeItems.isSelected = true
                        viewModel.selectedSizeName = sizeItems.name
                        viewModel.selectedSizePrice = sizeItems.price
                    }
                }
                if(viewModel.crusts!=null) {
                    crustAdapter.setCrusts(viewModel.crusts!!)
                }
                sizeAdapter.setSizes(crustItems.sizes)
            }
        }
    }

    private fun initListeners() {
        binding.btnAddToCart.setOnClickListener {
            viewModel.insertItem()
            viewModel.increasePizzaQuantity(args.pizzaInfo)
            this.dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onCrustSelected(crusts: PizzaModel.Crusts) {
        viewModel.crusts?.forEach { crustItem->
            if(crustItem.id == crusts.id) {
                crustItem.isSelected = true
                viewModel.selectedCrustName = crustItem.name
                crustItem.sizes.forEach{itemSizes->
                    if(itemSizes.id ==  crusts.defaultSize){
                        itemSizes.isSelected = true
                        viewModel.selectedSizeName = itemSizes.name
                        viewModel.selectedSizePrice = itemSizes.price
                    }
                    else{
                        itemSizes.isSelected = false
                    }
                }
                sizeAdapter.setSizes(crustItem.sizes)
            }
            else{
                crustItem.isSelected = false
            }
        }
        viewModel.crusts?.let { crustAdapter.setCrusts(it) }
    }

    override fun onSizeSelected(size: PizzaModel.Crusts.Sizes) {
        viewModel.crusts?.forEach { crustItem->
            if(crustItem.isSelected == true) {
                crustItem.sizes.forEach{itemSizes->
                    if(itemSizes.id ==  size.id){
                        itemSizes.isSelected = true
                        viewModel.selectedSizeName = size.name
                        viewModel.selectedSizePrice = size.price
                    }
                    else{
                        itemSizes.isSelected = false
                    }
                }
                sizeAdapter.setSizes(crustItem.sizes)
            }
            else{
                crustItem.isSelected = false
            }
        }
        viewModel.crusts?.let { crustAdapter.setCrusts(it) }
    }
}