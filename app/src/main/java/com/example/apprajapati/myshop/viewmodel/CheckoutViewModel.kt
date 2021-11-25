package com.example.apprajapati.myshop.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apprajapati.myshop.R
import com.example.apprajapati.myshop.data.ProductRepository

private const val PRICE_PER_QUANTITY = 5

class CheckoutViewModel(appContext: Application) : AndroidViewModel(appContext) {

    private val _quantity: MutableLiveData<Int> = MutableLiveData(0)
    private val _totalPrice: MutableLiveData<Int> = MutableLiveData()

    val quantity : LiveData<Int> = _quantity
    val totalPrice : LiveData<Int> = _totalPrice

    var productRepository : ProductRepository = ProductRepository()

    init {
        //either raw or assets both work.
        //productRepository.getDataFromResource(appContext, R.raw.olive_oils_data)
        //productRepository.getDataFromAsset(appContext, "olive_oils_data.json")

        val data = productRepository.getProducts(appContext, "olive_oils_data.json")

        data?.forEach {
            Log.i("Ajay", "$it.name")
        }
    }

    fun increaseQuantity(){
        _quantity.value = quantity.value!!.plus (1)
    }

    fun decreaseQuantity(){
        if(_quantity.value!! > 0){ // quantity cannot be in minus... safe check
            _quantity.value = quantity.value!!.minus(1)
        }
    }

    fun checkout(){
        _totalPrice.value =_quantity.value!! * PRICE_PER_QUANTITY
    }

}