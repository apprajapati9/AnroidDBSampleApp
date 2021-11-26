package com.example.apprajapati.myshop.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apprajapati.myshop.data.Product
import com.example.apprajapati.myshop.data.ProductRepository
import com.example.apprajapati.myshop.data.Stock
import com.example.apprajapati.myshop.data.StockRepository

private const val PRICE_PER_QUANTITY = 5

class CheckoutViewModel(appContext: Application) : AndroidViewModel(appContext) {

    private val _quantity: MutableLiveData<Int> = MutableLiveData(0)
    private val _totalPrice: MutableLiveData<Int> = MutableLiveData()

    val quantity : LiveData<Int> = _quantity
    val totalPrice : LiveData<Int> = _totalPrice

    private val _stockInfo :MutableLiveData<Stock> = MutableLiveData()
    val stockInfo: LiveData<Stock> = _stockInfo

    private val _products : MutableLiveData<List<Product>> = MutableLiveData()
    val products : LiveData<List<Product>> = _products

    private var  productRepository : ProductRepository = ProductRepository()
    private var stockRepository: StockRepository = StockRepository(appContext)

    init {
        //either raw or assets both work.
        //productRepository.getDataFromResource(appContext, R.raw.olive_oils_data)
        //productRepository.getDataFromAsset(appContext, "olive_oils_data.json")

        //Code is to show that how data can be fetched from local file, you can delete if needed.
        val data = productRepository.getProducts(appContext, "olive_oils_data.json")
        data?.let {
            _products.value = it
        }
    }

    //This function is for StockFragment only.
    fun getStockData(firmId : Int){
        val stock = stockRepository.getStockDataForFirm(firmId)
        stock?.let {
            _stockInfo.value = it
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