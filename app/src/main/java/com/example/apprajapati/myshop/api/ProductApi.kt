package com.example.apprajapati.myshop.api

import com.example.apprajapati.myshop.data.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("olive_oils_data.json")
    suspend fun getProducts() : Response<List<Product>>

    @GET("olive_oils_with_images_data.json")
    suspend fun getProductsWithImages() : Response<List<Product>>
}


//TODO : What is Asynchronous work? n how suspend works.