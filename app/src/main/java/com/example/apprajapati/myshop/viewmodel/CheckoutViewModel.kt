package com.example.apprajapati.myshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val PRICE_PER_QUANTITY = 5

class CheckoutViewModel : ViewModel() {

    private val _quantity: MutableLiveData<Int> = MutableLiveData(0)
    private val _totalPrice: MutableLiveData<Int> = MutableLiveData()

    val quantity : LiveData<Int> = _quantity
    val totalPrice : LiveData<Int> = _totalPrice

    init {

    }

    fun increaseQuantity(){
        _quantity.value = quantity.value!!.plus(1)
    }

    fun decreaseQuantity(){
        if(_quantity.value != 0){ // quantity cannot be in minus... safe check
            _quantity.value = quantity.value!!.minus(1)
        }
    }

    fun checkout(){
        _totalPrice.value =_quantity.value!! * PRICE_PER_QUANTITY
    }

}