package com.example.apprajapati.myshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        val checkoutModel by viewModels<CheckoutViewModel>()

        binding!!.addQuantityButton.setOnClickListener{
            checkoutModel.increaseQuantity()
        }

        binding!!.removeQuantityButton.setOnClickListener{
            checkoutModel.decreaseQuantity()
        }

        checkoutModel.quantity.observe(requireActivity()){
            binding!!.total.text = it.toString()
        }

        checkoutModel.totalPrice.observe(requireActivity()){
            showSnackbar(it)
        }

        binding!!.checkoutButton.setOnClickListener{
            checkoutModel.checkout()
        }
    }

    private fun showSnackbar(message: Int){
        Snackbar.make(binding!!.root, "Our observe count $message", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}