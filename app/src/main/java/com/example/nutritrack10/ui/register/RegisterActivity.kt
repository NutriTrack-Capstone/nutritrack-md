package com.example.nutritrack10.ui.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nutritrack10.Injection
import com.example.nutritrack10.R
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.databinding.ActivityRegisterBinding
import com.example.nutritrack10.network.ApiConfig
import com.example.nutritrack10.network.ApiService
import com.example.nutritrack10.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels {
//        RegisterViewModelFactory(UserRepository(ApiConfig.api))
        RegisterViewModelFactory(Injection.provideUserRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupAction()
    }

        private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            viewModel.registerUser(username, password)
        }
//        register here
        binding.buttonLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


        viewModel.registerResponse.observe(this, { response ->
            // Handle response
            val message = response?.message ?: "Registration failed"
            showToast(message)

            if (message.contains("SUCCESS CREATE USER", true)) {
                val username = binding.usernameEditText.text.toString()
                showToast("Registration successful for username: $username")

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}




//class RegisterActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityRegisterBinding
////    private lateinit var viewModel: RegisterViewModel
//    private lateinit var registerViewModel: RegisterViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRegisterBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.registerButton.setOnClickListener {
//            val username = binding.usernameEditText.text.toString()
//            val password = binding.passwordEditText.text.toString()
//
//            registerViewModel.registerUser(username, password) { registerResponse ->
//                if (registerResponse.success) {
//                    // Registrasi berhasil
//                    showSuccessDialog()
//                    val user = registerResponse.user
//                    user?.let {
//
//                    }
//                } else {
//                    // Registrasi gagal
//                }
//            }
//        }
//
//        setupView()
//        setupAction()

//        val userRepository = Injection.provideUserRepository(this)
//        viewModel = ViewModelProvider(this, RegisterViewModelFactory(userRepository)).get(RegisterViewModel::class.java)
//
//        registerViewModel.registrationStatus.observe(this, { result ->
//            if (result.isSuccess) {
//                showSuccessDialog()
//            } else {
//                val errorMessage = result.exceptionOrNull()?.message ?: "Terjadi kesalahan saat mendaftar!"
//                showErrorDialog(errorMessage)
//            }
//        })
//
//        viewModel.isLoading.observe(this, { isLoading ->
//            if (isLoading) {
//                binding.progressBar.visibility = View.VISIBLE
//            } else {
//                binding.progressBar.visibility = View.GONE
//            }
//        })
//
//        viewModel.errorMessage.observe(this, { errorMessage ->
//            if (errorMessage.isNotEmpty()) {
//                showErrorDialog(errorMessage)
//            }
//        })
//    }
//
//    private fun setupView() {
//        @Suppress("DEPRECATION")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.insetsController?.hide(WindowInsets.Type.statusBars())
//        } else {
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//        }
//        supportActionBar?.hide()
//    }
//
//    private fun setupAction() {
//        binding.signupButton.setOnClickListener {
//            val username = binding.usernameEditText.text.toString()
//            val password = binding.passwordEditText.text.toString()
//
//            viewModel.register(username, password)
//
//            AlertDialog.Builder(this).apply {
//                setTitle("Please Wait")
//                setMessage("Creating account with username")
//                create()
//                show()
//            }
//        }
        //            register here
//        binding.buttonLogin.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
//    }
//
//    private fun showSuccessDialog() {
//        AlertDialog.Builder(this).apply {
//            setTitle("Great!")
//            setMessage("Hello, Your account ${binding.usernameEditText.text.toString()} succesfully created.")
//            setPositiveButton("Next") { _, _ ->
//                finish()
//            }
//            create()
//            show()
//        }
//    }
//
//    private fun showErrorDialog(message: String) {
//        AlertDialog.Builder(this).apply {
//            setTitle("Oops!")
//            setMessage(message)
//            setPositiveButton("Retry", null)
//            create()
//            show()
//        }
//    }
//
//}