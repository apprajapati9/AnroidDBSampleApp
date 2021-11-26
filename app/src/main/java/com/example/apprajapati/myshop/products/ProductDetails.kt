package com.example.apprajapati.myshop.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.apprajapati.myshop.R
import com.example.apprajapati.myshop.databinding.FragmentProductDetailsBinding
import com.example.apprajapati.myshop.viewmodel.CheckoutViewModel

class ProductDetails : Fragment() {

    private var viewModel: CheckoutViewModel? = null
    private var _binding : FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_product_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this)[CheckoutViewModel::class.java]
        }

        viewModel?.selectedProduct?.observe(viewLifecycleOwner, {
            product ->
            //can be done two ways.. 1.
                binding.productImage.load(product.imageFile)
            //2 way
                with(product){
                    binding.productNameText.text = name
                    binding.descriptionText.text = description
                    binding.sizeText.text = getString(R.string.product_size_lable, size)
                    binding.priceText.text = price.toString()
                }
        })

        binding.addToCartButton.setOnClickListener{
            viewModel?.increaseQuantity()
            binding.addToCartButton.isEnabled = false
            binding.addToCartButton.text = getString(R.string.added_in_card)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}