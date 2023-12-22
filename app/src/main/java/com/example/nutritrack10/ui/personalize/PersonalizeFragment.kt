package com.example.nutritrack10.ui.personalize

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.nutritrack10.Injection
import com.example.nutritrack10.databinding.FragmentPersonalizeBinding
import com.example.nutritrack10.ui.login.LoginActivity

class PersonalizeFragment : Fragment() {

    private var _binding: FragmentPersonalizeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val viewModel: PersonalizeViewModel by viewModels{
        PersonalizeViewModelFactory(Injection.provideUserRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalizeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding){
            floatingActionButton.setOnClickListener {
                viewModel.logoutUser()
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }
            buttonSubmit.setOnClickListener {
                if(editTextAge.text.isEmpty()){
                    editTextAge.setText("0")
                }
                if(editTextHeight.text.isEmpty()){
                    editTextHeight.setText("0")
                }
                if(editTextWeight.text.isEmpty()){
                    editTextWeight.setText("0")
                }
                if (!radioButtonMale.isChecked && !radioButtonFemale.isChecked){
                    radioButtonMale.isChecked = true
                }
                var result = 2
                if (radioButtonMale.isChecked){
                    result = 1
                }
                viewModel.addProfileData(
                    editTextAge.text.toString().toInt(),
                    editTextHeight.text.toString().toInt(),
                    editTextWeight.text.toString().toInt(),
                    result
                )
                viewModel.responseSave.observe(requireActivity()){
                    if (it.message == "USER PROFILE ALREADY EXIST"){
                        viewModel.updateProfileData(
                            editTextAge.text.toString().toInt(),
                            editTextHeight.text.toString().toInt(),
                            editTextWeight.text.toString().toInt(),
                            result
                        )
                    }
                }
                Toast.makeText(requireContext(), "Profile data updated successfully", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}