package com.example.apprajapati.myshop

import android.annotation.SuppressLint
import android.icu.text.NumberFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apprajapati.myshop.data.Product
import com.example.apprajapati.myshop.databinding.ProductItemBinding


class ProductAdapter (private val productItems: List<Product>)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productItems[position]
        with(holder.binding) {
            productNameText.text = product.name
            sizeText.text = sizeText.context.resources.getString(
                R.string.product_size_lable,
                product.size)
            priceText.text = NumberFormat.getCurrencyInstance().format(product.price)

            productImage.load(product.imageFile)

        }
    }

    override fun getItemCount(): Int {
        return productItems.size
    }


    inner class ProductViewHolder
        (val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}