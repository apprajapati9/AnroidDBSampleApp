package com.example.apprajapati.myshop.api

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.apprajapati.myshop.api.ProductApi
import com.example.apprajapati.myshop.data.Product
import com.example.apprajapati.myshop.data.ProductDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

const val BASE_ENDPOINT_URL = "https://2873199.youcanlearnit.net/"
const val INTERNAL_PRODUCT_FILE_NAME = "product.json"

class ProductRepositoryRetrofit(private val app: Application) {

    private val moshi: Moshi by lazy {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.
        Builder().
        baseUrl(BASE_ENDPOINT_URL).
        addConverterFactory(MoshiConverterFactory.create(moshi)).
        build()
    }

    val api: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }

    suspend  fun getProducts(): List<Product> {
        val products = api.getProducts()
        Log.i("Ajay", "Data = ${products.body()}" )
        return if(products.isSuccessful) products.body()!! else emptyList()
    }

    private val productDao = ProductDatabase.getDatabase(app).productDao()

    suspend  fun getProductsWithImages(): List<Product> {

        //return data if cache exists
//        val productFromCache = readDataFromFile()
//        Log.i("Ajay", "loaded data from cache")
//        if(productFromCache.isNotEmpty()){
//            return productFromCache
//        }

        val response = api.getProductsWithImages()
        Log.i("Ajay", "Data = ${response.body()}" )
        return if(response.isSuccessful) {
            Log.i("Ajay", "loaded data from web service")
            val products = response.body()?: emptyList()

            storeDataInDb(products)
            //storeDataInFile()

            products

        } else emptyList()
    }

    suspend fun loadProducts() {
        val count = productDao.getCount()
        Log.i("Ajay", "loadProducts():: $count")
        if(count <= 0) {
            Log.i("Ajay", "loadProducts():: data gotten from web service")
            val response = api.getProductsWithImages()
            if(response.isSuccessful){
                val products = response.body() ?: emptyList()
                storeDataInDb(products)
            }
        }else{
            getProducts_()
        }
    }

    fun getProducts_(): Flow<List<Product>> {
        Log.i("Ajay", "loadProducts()_:: data gotten from database")
        return productDao.getProducts()
    }

    fun getTotalQuantity(): Flow<Int> {
        Log.i("Ajay", "getTotalQuantity()_:: function called")
        return productDao.getTotalQuantity()
    }


    suspend fun updateProduct(product: Product){
        return productDao.updateProduct(product)
    }

    private suspend fun storeDataInDb(products: List<Product>?){
        if(products != null){
            productDao.insertProducts(products)
        }
    }

    private fun storeDataInFile(products: List<Product>){

        val listType = Types.newParameterizedType(
            List::class.java, Product::class.java
        )

        val productString = moshi.adapter<List<Product>>(listType).toJson(products)

        /* app.filesDir to store in files directory, will be stored until app is removed

            app.cacheDir for cache directory, will clean this file when device storage is low
         */
        val file = File(app.cacheDir, INTERNAL_PRODUCT_FILE_NAME)
        file.writeText(productString)
    }

    private fun storeDataInExternalStorage(products: List<Product>) {
        //First request permission since it is a dangerous permission
        // for transparency and security reason should take consent of a user

        if (ContextCompat.checkSelfPermission(
                app,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            val listType = Types.newParameterizedType(
                List::class.java, Product::class.java
            )

            val productString = moshi.adapter<List<Product>>(listType).toJson(products)

            val file = File(
                app.getExternalFilesDir("products"),
                INTERNAL_PRODUCT_FILE_NAME
            ) //same goes for reading for reading external dir
            //path in device file exp = sdcard/android/data/package.name/files/directory_named/filename

            file.writeText(productString)

        }
    }

    private fun readDataFromFile() : List<Product>{

        val file = File(app.cacheDir, INTERNAL_PRODUCT_FILE_NAME)

        val json = if(file.exists()) file.readText() else null

        return if (json == null) emptyList()
        else {
            val listTYpe = Types.newParameterizedType(List::class.java, Product::class.java)
            moshi.adapter<List<Product>>(listTYpe).fromJson(json) ?: emptyList()
        }
    }
}