package com.example.apprajapati.myshop.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProducts(products: List<Product>)
}