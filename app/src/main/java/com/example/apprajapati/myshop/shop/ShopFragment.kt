package com.example.apprajapati.myshop.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apprajapati.myshop.R
import com.example.apprajapati.myshop.data.Product
import com.example.apprajapati.myshop.databinding.ShopFragmentBinding
import com.example.apprajapati.myshop.viewmodel.CheckoutViewModel

class ShopFragment : Fragment() {

    private var _binding: ShopFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.shop_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myModel = activity?.run{
            ViewModelProvider(this)[CheckoutViewModel::class.java]
        }

        // ----------------- NOTE -----------------------
        //Below code is how you should call obverver but it causes error that cannot add same obverver with different lifecycle, uncomment to test.
        //so below note is working code where object : Ob is separated to create diff observer.
        /*
            products from local json file.
         */
//        myModel?.local_products?.observe(this, {
//            local_product ->
//            val productNames = StringBuilder()
//            local_product.forEach {
//                productNames.appendLine(it.name)
//            }
//            //Note: Uncomment to display local data.
//            // binding.cartContentText.setText(productNames.toString())
//        })

        /*
        product observer for data gotten from online retrofit API service.
         */
//        myModel?.products?.observe(this, {
//                product ->
//            val productNames = StringBuilder()
//            product.forEach {
//                productNames.appendLine(it.name)
//            }
//            //Note: Uncomment to display local data.
//            binding.cartContentText.setText(productNames.toString())
//        })

        // ----------------- NOTE ENDS -----------------------



        myModel?.local_products?.observe(viewLifecycleOwner, object : Observer<List<Product>>{
            override fun onChanged(local_product: List<Product>) {

                val productNames = StringBuilder()
                local_product.forEach {
                    productNames.appendLine(it.name)
                }
                //Note: Uncomment to display local data.
                // binding.cartContentText.setText(productNames.toString())
            }

        })


        myModel?.products?.observe(viewLifecycleOwner, object : Observer<List<Product>>{
            override fun onChanged(product: List<Product>) {

                val productNames = StringBuilder()
                product.forEach {
                    productNames.appendLine(it.name)
                }
                //Note: Uncomment to display local data.
                binding.cartContentText.setText(productNames.toString())

            }

        })


        binding.add.setOnClickListener {  myModel?.increaseQuantity() }
        binding.minus.setOnClickListener { (myModel?.decreaseQuantity()) }

    }

//    private fun upgradeBadge(item: Int?) {
//        val badge = binding.root.getOrCreateBadge(R.id.action_shop)
//        if(item!! > 0){
//            badge.number = item
//            badge.isVisible = true
//        }else{
//            badge.clearNumber()
//            badge.isVisible = false
//        }
//    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}