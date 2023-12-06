package com.example.nutritrack10.ui.overview

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritrack10.databinding.FragmentOverviewBinding
import com.example.nutritrack10.model.Food


class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter // Ganti YourAdapter dengan nama adapter Anda
    private val dataList = ArrayList<Food>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val overviewViewModel =
            ViewModelProvider(this).get(OverviewViewModel::class.java)

        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inisialisasi RecyclerView menggunakan data binding
        binding.rvFoodrecommendation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        // Contoh data menggunakan Parcelable
        dataList.add(Food("Food 1", "100", R.drawable.alert_light_frame))
        dataList.add(Food("Food 2", "150", R.drawable.star_on))
        dataList.add(Food("Food 3", "200", R.drawable.screen_background_dark))

        // Inisialisasi dan atur adapter RecyclerView
        adapter = FoodAdapter(dataList)
        binding.rvFoodrecommendation.adapter = adapter

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
