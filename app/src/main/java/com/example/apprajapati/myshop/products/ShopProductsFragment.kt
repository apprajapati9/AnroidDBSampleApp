package com.example.apprajapati.myshop.products

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.apprajapati.myshop.R
import com.example.apprajapati.myshop.data.Product
import com.example.apprajapati.myshop.databinding.ShopProductsFragmentBinding
import com.example.apprajapati.myshop.viewmodel.CheckoutViewModel
import com.google.android.material.snackbar.Snackbar

class ShopProductsFragment : Fragment() {

    private var viewModel: CheckoutViewModel? = null
    private var _binding: ShopProductsFragmentBinding? = null
    private val binding get() = _binding!!

    private val onItemClick: (Product) -> Unit = {
            product -> Log.i("Ajay", "$product.name")
        viewModel?.selectedProduct?.value = product
        findNavController().navigate(R.id.action_fragment_shop_online_to_productDetails)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){
            isGranted ->
                if(isGranted){
                    Snackbar.make(
                        binding.root,
                        "Storage Permission Granted",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
        }
    //NOTE: ctrl + p or cmd+p to see args values required when inserting values in a function

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater,
            R.layout.shop_products_fragment,  container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkStoragePermission()

        viewModel = activity?.run {
            ViewModelProvider(this)[CheckoutViewModel::class.java]
        }

        viewModel?.products?.observe(viewLifecycleOwner, {
            products ->
                binding.productList.adapter = ProductAdapter(products, onItemClick)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkStoragePermission(){
        if(ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED){

            requestPermissionLauncher.launch(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }
}