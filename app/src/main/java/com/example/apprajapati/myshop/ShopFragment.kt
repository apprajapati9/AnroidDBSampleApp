package com.example.apprajapati.myshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.apprajapati.myshop.databinding.ShopFragmentBinding
import com.example.apprajapati.myshop.viewmodel.CheckoutViewModel

class ShopFragment : Fragment() {

    private lateinit var binding: ShopFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shop_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myModel = activity?.run{
            ViewModelProvider(requireActivity())[CheckoutViewModel::class.java]
        }

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
        super.onDestroyView()
    }
}