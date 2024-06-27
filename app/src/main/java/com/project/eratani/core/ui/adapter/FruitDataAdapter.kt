package com.project.eratani.core.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.eratani.databinding.ItemListFruitBinding

class FruitDataAdapter(private var listFruit: List<String>): RecyclerView.Adapter<FruitDataAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListFruitBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listFruit.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listFruit[position]

        holder.binding.apply {
            tvFruitName.text = data
        }
    }

    fun updateData(newFruitList: List<String>) {
        listFruit = newFruitList
        notifyDataSetChanged()
    }
}