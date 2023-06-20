package com.example.apprajapati.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.apprajapati.myshop.databinding.ActivityMainBinding
import com.example.apprajapati.myshop.viewmodel.CheckoutViewModel
import com.example.apprajapati.myshop.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object{
        private const val TAG = "LifeCycleMonitor"
        // you can also get class name by ClassName::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val obs = MyActivityLifecycleObserver()
        lifecycle.addObserver(obs) //Android lifecycle methods.
        /*
            -----viewBinding true vs dataBinding true in gradle-----
            There are two ways to inflate setcontentview and using viewBinding you can do by following..

            binding = ActivityMainBinding.inflate(layoutInflater)
             setContentView(binding.root)

             If you just want to replace findViewById methods then viewBinding provides speed and efficiency
             however, databinding is superset of viewBinding in which you can handle data as well, with ViewBinding
             you can just eliminate boilerplate findViewById codes.
         */

        /*
            You can do the same as above but if you are doing data binding then, you need to use this.
         */


        //Observer class needs observer and observer is MainActivity so you can do the following to observe lifecycle events...
        //lifecycle.addObserver(MyActivityLifecycleObserver())
        // Appcompatactivity already impplements the lifecycle owner interface.

       //--- binding.collapsingToolbar.title = getString(R.string.app_name)
        //binding.toolbar.title = getString(R.string.app_name)

        //val viewModel: MainViewModel by viewModels()

        /* --- Replaced by Navigation component library to handle fragment transaction by itself using graph


        supportFragmentManager.commit {
            add(R.id.fragmentContainer, HomeFragment::class.java, null)
        }

        binding.tabs.setOnNavigationItemSelectedListener {
            item ->
                when(item.itemId) {
                    R.id.action_home -> goHome()
                    R.id.action_shop -> goShop()
                    R.id.action_tours -> goTour()
                    R.id.action_checkout -> goCheckout()
                    R.id.product_list -> goProductList()
                    else -> false
                }
                
        }


         */

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment

        val navController = navHostFragment.navController
        binding.tabs.setupWithNavController(navController)


        val viewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        viewModel.quantity.observe(this){
            upgradeBadge(it)
        }
    }

    private fun upgradeBadge(item: Int?) {
        val badge = binding.tabs.getOrCreateBadge(R.id.action_shop)
        if(item!! > 0){
            badge.number = item
            badge.isVisible = true
        }else{
            badge.clearNumber()
            badge.isVisible = false
        }
    }

    private fun showSnackbar(message: Int){
        Snackbar.make(binding.root, "Our observe count $message", Snackbar.LENGTH_SHORT).show()
    }
}

/*
Error : could not find the DataBindingComponent class

What solved the problem :
    Rebuilding project.
    Hint: DataBindingUitl class has red indicator saying "Library source does not match the bytecode for class"
    so did rebuild to solve this and in return automatically solved the databinding component not found issue as well.

 */