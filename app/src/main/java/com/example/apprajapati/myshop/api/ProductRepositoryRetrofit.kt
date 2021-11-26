package com.example.apprajapati.myshop.api

import com.example.apprajapati.myshop.api.ProductApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "https://2873199.youcanlearnit.net/"

class ProductRepositoryRetrofit {

    private val retrofit: Retrofit by lazy {

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        Retrofit.
        Builder().
        baseUrl(BASE_ENDPOINT_URL).
        addConverterFactory(MoshiConverterFactory.create(moshi)).
        build()
    }

    private val api: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }
}