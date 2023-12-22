package com.example.nutritrack10.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritrack10.Injection
import com.example.nutritrack10.data.remote.response.DataItem
import com.example.nutritrack10.databinding.FragmentExploreBinding
import com.example.nutritrack10.ui.login.LoginViewModelFactory

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExploreViewModel by viewModels {
        ExploreViewModelFactory(Injection.provideUserRepository(requireContext()))
    }

    private lateinit var exploreAdapter: ExploreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        exploreAdapter = ExploreAdapter(emptyList()) // Initialize adapter with an empty list
        binding.rvUser.adapter = exploreAdapter
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    viewModel.getExploreData(newText)
                    binding.progressBar.visibility = View.GONE
                }
                return true
            }
        })

        observeViewModel()

        return root
    }

    private fun observeViewModel() {
        viewModel.response.observe(viewLifecycleOwner) { exploreResponse ->
            exploreAdapter.updateData(exploreResponse.data ?: emptyList())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}