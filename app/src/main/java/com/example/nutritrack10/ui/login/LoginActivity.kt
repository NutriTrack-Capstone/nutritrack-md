package com.example.nutritrack10.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.ColorSpace
import android.net.http.HttpException
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import com.example.nutritrack10.MainActivity
import com.example.nutritrack10.R
import com.example.nutritrack10.data.remote.UserRepository
import com.example.nutritrack10.databinding.ActivityLoginBinding
import com.example.nutritrack10.network.ApiConfig
import com.example.nutritrack10.ui.personalize.PersonalizeFragment
import com.example.nutritrack10.ui.register.RegisterActivity
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        isLogged()
        setupView()
        setupAction()
        playAnimation()
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
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            binding.progressBar.visibility = View.VISIBLE

            // Call the login function in the ViewModel
            viewModel.login(username, password)

        }

        binding.buttonToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        viewModel.loginResponse.observe(this) { response ->
            binding.progressBar.visibility = View.GONE

            showToast(response?.message ?: "Login failed")

            if (response?.message?.contains("Login success", true) == true) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

//            if (response?.message?.contains("Login success", true) == true) {
//                // Menggunakan FragmentManager untuk mengganti Fragment
//                val fragmentManager = supportFragmentManager
//                val fragmentTransaction = fragmentManager.beginTransaction()
//
//                // Ganti "YourFragment" dengan nama Fragment yang ingin Anda arahkan
//                val yourFragment = PersonalizeFragment()
//
//                // Mengganti Fragment yang ada di container dengan Fragment baru
//                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, yourFragment)
//
//                // Mengganti Fragment tanpa menambahkannya ke back stack
//                fragmentTransaction.addToBackStack(null)
//
//                // Melakukan commit untuk mengaplikasikan perubahan
//                fragmentTransaction.commit()
//            }

        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun isLogged(){
        var result: Boolean?
        runBlocking {
            result = viewModel.isLoggedIn()
        }
        if (result == true) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //    property animation
    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_Y, -10f, 0f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val message =
            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
        val usernameTextView =
            ObjectAnimator.ofFloat(binding.usernameEditText, View.ALPHA, 1f).setDuration(100)
        val usernameEditTextLayout =
            ObjectAnimator.ofFloat(binding.usernameEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                message,
                usernameTextView,
                usernameEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 100
        }.start()
    }

}



//class LoginActivity : AppCompatActivity() {
////    private val viewModel by viewModels<LoginViewModel> {
////        LoginViewModelFactory.getInstance(this)
////    }
//    private lateinit var binding: ActivityLoginBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.loginButton.setOnClickListener {
//            val username = binding.usernameEditText.text.toString()
//            val password = binding.passwordEditText.text.toString()
//
//            binding.progressBar.visibility = View.VISIBLE
//            // Logika login
//        }
//
//        binding.buttonToRegister.setOnClickListener {
//            startActivity(Intent(this, RegisterActivity::class.java))
//        }
//
//        setupView()
//        setupAction()
//        playAnimation()
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
//        binding.loginButton.setOnClickListener {
//            val username = binding.usernameEditText.text.toString()
//            val password = binding.passwordEditText.text.toString()
//
//            binding.progressBar.visibility = View.VISIBLE
//            binding.loginButton.setOnClickListener {
//                startActivity(Intent(this, MainActivity::class.java))
//            }
//        }
//
////        viewModel.loginStatus.observe(this, { result ->
////            // Menyembunyikan ProgressBar saat proses login selesai
////            binding.progressBar.visibility = View.GONE
////
////            if (result != null && result.isSuccess) {
////                showSuccessDialog()
////            } else if (result != null && result.isFailure) {
////                showErrorDialog(
////                    result.exceptionOrNull()?.message ?: "Login error."
////                )
////            }
////        })
//    }
//
//    private fun showSuccessDialog() {
//        AlertDialog.Builder(this).apply {
//            setTitle("Login successfully!")
//            setMessage("Next and share your story")
//            setPositiveButton("Next") { _, _ ->
//                val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                startActivity(intent)
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

//    viewModelScope.launch(Dispatchers.IO) {
//        try{val apiResponse = apiEndPointsInterface.loginUser(loginRequestModel)
//            returnLoginResponse(apiResponse)}
//        catch(exception:Exception) {
//            if(exception is HttpException && t.statusCode==401)
//                sessionExpired(BaseApplication.getApplication())}
//    }



//override fun onFailure(call: Call<ColorSpace.Model.Result>, t: Throwable) {
//    if(t is HttpException && t.statusCode==401)
//        sessionExpired(BaseApplication.getApplication())
//}