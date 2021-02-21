package com.example.apprajapati.myshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.apprajapati.myshop.databinding.HomeFragmentBinding
import com.example.apprajapati.myshop.databinding.HomeFragmentBindingImpl

/*
https://developer.android.com/topic/libraries/view-binding
To properly do databinding in a fragment
 */
class HomeFragment : Fragment() {

    private var binding: HomeFragmentBinding? = null

//    private var _binding : HomeFragmentBindingImpl? = null
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //ONE WAY... using databindingUtil...
        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment, container, false)

        lifecycle.addObserver(MyActivityLifecycleObserver())

        binding!!.homeFragmentToolbar.title= getString(R.string.app_name)
        return binding!!.root

        //Second way
//        return HomeFragmentBinding.inflate(inflater, container, false).apply {
//            lifecycle.addObserver(MyActivityLifecycleObserver())
//        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        //consider not storing the binding instance in a field, if not needed.
        binding = null
        super.onDestroyView()
    }
}