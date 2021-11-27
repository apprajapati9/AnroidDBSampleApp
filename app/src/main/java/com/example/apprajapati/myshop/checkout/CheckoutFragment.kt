package com.example.apprajapati.myshop.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.apprajapati.myshop.R
import com.example.apprajapati.myshop.databinding.CheckoutFragmentBinding
import com.example.apprajapati.myshop.viewmodel.CheckoutViewModel
import com.google.android.material.snackbar.Snackbar

class CheckoutFragment: Fragment() {

    private var binding : CheckoutFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.checkout_fragment, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this initializes new model everytime
        //val checkoutModel by viewModels<CheckoutViewModel>()

        // if you want to use from activity to have access to all fragments then should use following..
        val checkoutModel = activity?.run {
            ViewModelProvider(requireActivity())[CheckoutViewModel::class.java]
        }

        binding!!.addQuantityButton.setOnClickListener{
            checkoutModel!!.increaseQuantity()
        }

        binding!!.removeQuantityButton.setOnClickListener{
            checkoutModel!!.decreaseQuantity()
        }

        checkoutModel!!.quantity.observe(requireActivity()){
            updateQuantity(it)
        }

        checkoutModel.totalPrice.observe(requireActivity()){
            showSnackbar(it)
        }

        binding!!.checkoutButton.setOnClickListener{
            checkoutModel.checkout()
        }
    }

    private fun updateQuantity(quantity: Int){
        if(binding!= null){
            binding!!.total.text = quantity.toString()
        }
    }

    private fun showSnackbar(message: Int){
        if(binding != null){
            Snackbar.make(binding!!.root, "Our observe count $message", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}