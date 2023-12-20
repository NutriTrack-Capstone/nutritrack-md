package com.example.nutritrack10.ui.overview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritrack10.R
import com.example.nutritrack10.databinding.ItemFoodBinding
import com.example.nutritrack10.data.local.model.Food

class FoodAdapter(private val dataList: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = dataList[position]

        // Set data ke dalam view holder
        holder.bind(food)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(food: Food) {
            // Set data dari objek Food ke dalam view holder
            binding.tvFoodname.text = food.name
            binding.tvFoodcal.text = food.cal
            binding.imgFood.setImageResource(food.photo)
        }
    }
}