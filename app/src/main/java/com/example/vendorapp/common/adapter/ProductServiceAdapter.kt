package com.example.vendorapp.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vendorapp.R
import com.example.vendorapp.common.data.model.response.ProductService

class ProductServiceAdapter(private val context: Context, private val products: List<ProductService?>?) : RecyclerView.Adapter<ProductServiceAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.productName)
        val productDescription: TextView = view.findViewById(R.id.productDescription)
        val productImage: ImageView = view.findViewById(R.id.imgProductImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products?.get(position)
        holder.productName.text = if (!product?.name.isNullOrEmpty()) {
            product?.name?.trim()
        } else {
            "Dummy Product Name"
        }

        holder.productDescription.text = if (!product?.description.isNullOrEmpty()) {
            product?.description?.trim()
        } else {
            "No description available."
        }
        Glide.with(context)
            .load(product?.image)
            .into(holder.productImage)

    }

    override fun getItemCount() = products?.size?:0
}