package com.example.apprajapati.myshop.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apprajapati.myshop.R
import com.example.apprajapati.myshop.databinding.HomeFragmentBinding
import com.example.apprajapati.myshop.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

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
    ): View {

        //ONE WAY... using databindingUtil...
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        setHasOptionsMenu(true) // To enable option menu

        binding!!.homeFragmentToolbar.inflateMenu(R.menu.share_menu)
        binding!!.homeFragmentToolbar.setOnMenuItemClickListener(
                Toolbar.OnMenuItemClickListener {
                    when (it.itemId){
                        R.id.mi_share -> {
                            handleShare()
                            true
                        }
                        else -> false
                    }

                }
        )

        //lifecycle.addObserver(MyActivityLifecycleObserver())

        binding!!.homeFragmentToolbar.title= getString(R.string.app_name)
        return binding!!.root

        //Second way
//        return HomeFragmentBinding.inflate(inflater, container, false).apply {
//            lifecycle.addObserver(MyActivityLifecycleObserver())
//        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //To use viewModels<>() you need android activity ktx library
        val viewModel: MainViewModel by viewModels()
        // Lazy view model so it won't create an instance until it is needed in a component.
        // by running this, it won't print init{} log because it hasn't been created .

            //viewModel.loadData()
        // now it will instantiate and will call init{} in HomeViewModel

        //------------Lamda showcase ------------
        // The following can be done using Lamdas
        /*
        binding.floatingbuttonShop.setOnClickListener( object : View.OnClickListener{
            override fun onClick(v: View?) {
                Snackbar.make(v!!, "Clicked", Snackbar.LENGTH_SHORT).show()
            }

        })
         */

        // lamdas create cleaner and readable code
//        binding.floatingbuttonShop.setOnClickListener{
//            view ->
//            Snackbar.make(view!!, "Clicked", Snackbar.LENGTH_SHORT).show()
//        }

        //------------Lamda showcase ends------------
        //adding viewmodel observer...

        viewModel.info.observe(requireActivity()){
            if(it != 0){
                showSnackbar(it)
            }
        //First time it will appear because init{} changes its value to 0, so if will prevent 0 init{} trigger
        }

        // view ->  could be replaced by _ -> .. Name shadowed.
        binding!!.homeFloatingbuttonShop.setOnClickListener{
            view -> viewModel.loadData()
        }
    }

    private fun showSnackbar(message: Int){
        Snackbar.make(binding!!.root, "Our observe count $message", Snackbar.LENGTH_SHORT).show()
    }

    private fun handleShare(){
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Implicit intent text!")
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        //consider not storing the binding instance in a field, if not needed.
        binding = null
        super.onDestroyView()
    }
}