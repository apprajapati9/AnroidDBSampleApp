package com.example.apprajapati.myshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.apprajapati.myshop.databinding.TourFragmentBindingImpl

class TourFragment : Fragment() {

    private lateinit var binding: TourFragmentBindingImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.tour_fragment, container, false)
        return binding.root
    }
}