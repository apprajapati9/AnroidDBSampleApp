package com.example.apprajapati.myshop.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _info : MutableLiveData<Int> = MutableLiveData()

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