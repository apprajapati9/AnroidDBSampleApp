package com.example.apprajapati.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import com.example.apprajapati.myshop.databinding.ActivityMainBinding
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

        /*
            viewBinding true
            There are two ways to inflate setcontentview and using viewBinding you can do by following..

            binding = ActivityMainBinding.inflate(layoutInflater)
             setContentView(binding.root)

             If you just want to replace findViewById methods then viewBinding provides speed and efficiency
             however, databinding is superset of viewBinding in which you can handle data as well, with ViewBinding
             you can just eliminate boilerplace findViewById codes.
         */

        /*
            You can do the same as above but if you are doing data binding then, you need to use this.
         */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Observer class needs observer and observer is MainActivity so you can do the following to observe lifecycle events...
        lifecycle.addObserver(MyActivityLifecycleObserver())
        // Appcompatactivity already impplements the lifecycle owner interface.

        binding.collapsingToolbar.title = getString(R.string.app_name)
        //binding.toolbar.title = getString(R.string.app_name)

        //To use viewModels<>() you need android activity ktx library
        val viewModel by viewModels<MainViewModel>()
        // Lazy view model so it won't create an instance until it is needed in a component.
        // by running this, it won't print init{} log because it hasn't been created .

        viewModel.loadData()
        // now it will instantiate and will call init{} in HomeViewModel

        // The following can be done using Lamdas
        /*
        binding.floatingbuttonShop.setOnClickListener( object : View.OnClickListener{
            override fun onClick(v: View?) {
                Snackbar.make(v!!, "Clicked", Snackbar.LENGTH_SHORT).show()
            }

        })
         */

        // lamdas create cleaner and readable code
        binding.floatingbuttonShop.setOnClickListener{
            view ->
            Snackbar.make(view!!, "Clicked", Snackbar.LENGTH_SHORT).show()
        }

    }
}