package com.example.apprajapati.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import com.example.apprajapati.myshop.databinding.ActivityMainBinding

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
    }
}