package com.example.vendorapp.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vendorapp.R
import com.example.vendorapp.common.data.model.response.Category

class CategoryAdapter(
    private val context: Context,
    private val categories: List<Category?>?
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        categories?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = categories?.size?:0

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.txtCategoryTitle)
        private val productsRecyclerView: RecyclerView = itemView.findViewById(R.id.productsRecyclerView)

        fun bind(category: Category) {
            title.text = category.name
            productsRecyclerView.adapter = ProductServiceAdapter(context, category.productServices)
            productsRecyclerView.layoutManager = LinearLayoutManager(context)
        }
    }
}
