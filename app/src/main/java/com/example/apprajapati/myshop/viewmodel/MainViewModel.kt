package com.example.apprajapati.myshop.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    //Only viewModel can update this data
    private val _info : MutableLiveData<Int> = MutableLiveData()

    // To expose viewmodel data to activities / fragment but they cannot modify any data just able to read/show.
    val info: LiveData<Int> = _info
    //any time we change live the private livedata var, public one is updated as well.

    init{
        Log.i("MainViewModel", "created")
        _info.value = 0
    }

    fun loadData(){
        Log.i("MainViewModel", "load data")
        _info.value = _info.value!! + 1
        Log.i("MainViewModel", "info value ${_info.value}")
    }


}