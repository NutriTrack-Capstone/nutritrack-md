package com.example.nutritrack10.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritrack10.data.remote.model.FoodItem
import com.example.nutritrack10.databinding.ItemExploreBinding

class ExploreAdapter(private val dataList: List<FoodItem>)
    : RecyclerView.Adapter<ExploreAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
//            ItemExploreBinding.inflate(LayoutInflater.from(parent.context))
            ItemExploreBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(private val binding: ItemExploreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(foodItem: FoodItem) {
            // Set data dari objek Food ke dalam view holder
            binding.tvTitleItemfoodexplore.text = foodItem.name
            binding.tvTitleItemfoodcal.text = foodItem.calories.toString()
        }
    }
}