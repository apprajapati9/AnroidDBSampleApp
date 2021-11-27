package com.example.apprajapati.myshop.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProducts(products: List<Product>)

    @Query("SELECT * FROM products")
    fun getProducts() : Flow<List<Product>>

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getCount() : Int
}