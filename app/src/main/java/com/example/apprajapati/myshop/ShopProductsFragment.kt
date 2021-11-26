package com.example.apprajapati.myshop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.apprajapati.myshop.data.Product
import com.example.apprajapati.myshop.databinding.ShopProductsFragmentBinding
import com.example.apprajapati.myshop.viewmodel.CheckoutViewModel

class ShopProductsFragment : Fragment() {

    private var _binding: ShopProductsFragmentBinding? = null
    private val binding get() = _binding!!

    private val onItemClick: (Product) -> Unit = {
            product -> Log.i("Ajay", "$product.name")
        findNavController().navigate(R.id.action_fragment_shop_online_to_productDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater,R.layout.shop_products_fragment,  container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productViewModel = activity?.run {
            ViewModelProvider(this)[CheckoutViewModel::class.java]
        }

        productViewModel?.products?.observe(viewLifecycleOwner, {
            products ->
                binding.productList.adapter = ProductAdapter(products, onItemClick)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}