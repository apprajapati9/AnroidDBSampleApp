package com.example.apprajapati.myshop.api

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.apprajapati.myshop.api.ProductApi
import com.example.apprajapati.myshop.data.Product
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

const val BASE_ENDPOINT_URL = "https://2873199.youcanlearnit.net/"

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

    suspend  fun getProductsWithImages(): List<Product> {
        val response = api.getProductsWithImages()
        Log.i("Ajay", "Data = ${response.body()}" )
        return if(response.isSuccessful) {

            val products = response.body()?: emptyList()

            storeDataInFile(products)

            products

        } else emptyList()
    }

    private fun storeDataInFile(products: List<Product>){

        val listType = Types.newParameterizedType(
            List::class.java, Product::class.java
        )

        val productString = moshi.adapter<List<Product>>(listType).toJson(products)

        //app.filesDir to store in files directory, app.cacheDir for cache directory
        val file = File(app.cacheDir, "product.json")
        file.writeText(productString)
    }
}