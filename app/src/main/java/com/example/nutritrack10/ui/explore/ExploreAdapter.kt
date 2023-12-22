package com.example.nutritrack10.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nutritrack10.R
import com.example.nutritrack10.data.remote.model.FoodItem
import com.example.nutritrack10.data.remote.response.DataItem
import com.example.nutritrack10.databinding.ItemExploreBinding
class ExploreAdapter(private var foodList: List<DataItem>) : RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.tv_title_itemfoodexplore)
        val textViewCalories: TextView = itemView.findViewById(R.id.tv_title_itemfoodcal)
        val imageViewFood: ImageView = itemView.findViewById(R.id.img_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_explore, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foodList[position]

        holder.textViewName.text = item.name
//        holder.textViewCalories.text = item.calories.toString()
        val caloriesText = "${item.calories} kcal"
        holder.textViewCalories.text = caloriesText

        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(item.image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.imageViewFood)
    }

    override fun getItemCount(): Int = foodList.size

    // Update data and notify adapter
    fun updateData(newData: List<DataItem?>?) {
        foodList = newData?.filterNotNull() ?: emptyList()
        notifyDataSetChanged()
    }
}
