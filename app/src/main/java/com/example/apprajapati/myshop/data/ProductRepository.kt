package com.example.apprajapati.myshop.data

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProductRepository {

    //To read from raw directory..
    fun getDataFromResource(context: Context, resourceId: Int): String? {

        context.resources.openRawResource(resourceId)
            .bufferedReader().use {
                it.readText()
            }

        // TODO: understand what use and bufferreader does. also check boilerplate code for reading file.

        return null
    }

    /*
        To create Asset folder: app (right-click) -> New folder -> Asset ->
                                    choose App so you can choose Main as root folder.
     */

    //To read from Asset folder.
    fun getDataFromAsset(context: Context, fileName: String) : String {
        return context.resources.assets.open(fileName)
            .bufferedReader().use {
                it.readText()
            }
    }

    /*
        Json deserialization is converting json string into data-object
        serialization is data object to Json string.

     */

    fun getProducts(context: Context, fileName: String) : List<Product>?  {


        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        //From what class to what type...listType, Product mapping to Arraylist
        val listType = Types.newParameterizedType(
            List::class.java, Product::class.java
        )

        val adaptor : JsonAdapter<List<Product>> = moshi.adapter(listType)
        return adaptor.fromJson(getDataFromAsset(context, fileName))
    }

}