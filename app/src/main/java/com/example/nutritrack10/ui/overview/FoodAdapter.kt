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
import com.example.nutritrack10.model.Food


//class FoodAdapter(private val listFood: List<Food>) : RecyclerView.Adapter<FoodAdapter.ListViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
//        return ListViewHolder(view)
//    }
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val food = listFood[position]
////        val (name, cal, photo) = listFood[position]
////        holder.imgPhoto.setImageResource(photo)
////        holder.tvName.text = name
////        holder.tvDescription.text = cal
//    }
//
//    override fun getItemCount(): Int = listFood.size
//
//    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imgPhoto: ImageView = itemView.findViewById(R.id.img_food)
//        val tvName: TextView = itemView.findViewById(R.id.tv_foodname)
//        val tvDescription: TextView = itemView.findViewById(R.id.tv_foodcal)
//    }
//}
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