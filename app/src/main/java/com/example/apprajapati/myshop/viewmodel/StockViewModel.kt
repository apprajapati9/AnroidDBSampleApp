package com.example.apprajapati.myshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apprajapati.myshop.data.Stock
import com.example.apprajapati.myshop.data.StockRepository

/*
    Note: can use CheckoutViewModel as well because same code is in there.
 */
class StockViewModel(appContext : Application) : AndroidViewModel(appContext) {

    val _stockInfo : MutableLiveData<Stock> = MutableLiveData()
    val stockInfo : LiveData<Stock> get() = _stockInfo

    val stockRepo : StockRepository = StockRepository(appContext)

    fun getStockData(firmId: Int) {
        val stock = stockRepo.getStockDataForFirm(firmId)

        stock?.let {
            _stockInfo.value = it
        }

        /*

        TODO: what's difference between

        1

        _stockInfo.value = stock

        2
        stock?.let {
            _stockInfo.value = it
        }

         */


    }
}