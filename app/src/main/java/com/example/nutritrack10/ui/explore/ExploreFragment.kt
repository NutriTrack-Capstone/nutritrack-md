package com.example.nutritrack10.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutritrack10.Injection
import com.example.nutritrack10.databinding.FragmentExploreBinding
import com.example.nutritrack10.ui.login.LoginViewModelFactory

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel : ExploreViewModel by viewModels {
        ExploreViewModelFactory(Injection.provideUserRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        with(binding){
            viewModel.getExploreData("")
            viewModel.response.observe(requireActivity()){
                rvUser.adapter = ExploreAdapter(
                    it.foodList!!
                )
                rvUser.adapter?.notifyDataSetChanged()
            }
            rvUser.layoutManager = LinearLayoutManager(requireContext())
            searchView.setOnQueryTextListener(object : OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null) {
                        viewModel.getExploreData(p0)
                        progressBar.visibility = View.GONE
                    }
                    return true
                }
            })
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}