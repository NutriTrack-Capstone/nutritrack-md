package com.example.nutritrack10.ui.scanresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.nutritrack10.Injection
import com.example.nutritrack10.MainActivity
import com.example.nutritrack10.databinding.ActivityCameraResultBinding

class CameraResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<CameraResultViewModel> {
            CameraResultViewModelFactory(Injection.provideUserRepository(this))
        }

        val name = intent.getStringExtra("name")
        val score = intent.getFloatExtra("score",0.0f)
        val calories = intent.getIntExtra("calories", 0)
        val carbohydrates = intent.getIntExtra("carbohydrates", 0)
        val fats = intent.getIntExtra("fats", 0)
        val proteins = intent.getIntExtra("proteins", 0)


        viewModel.predictionResponse.observe(this) { response ->
            response?.let {
                Log.d("Update status", it.toString())
                if (it.status == "success") {
                    Log.d("UPDATE STATUS", "berhasil")

                   startActivity(Intent(this@CameraResultActivity, MainActivity::class.java))
                    finish()

                } else {
                    Log.d("UPDATE STATUS", "gagal")
                }
            }
        }

        binding.updateButton.setOnClickListener{
            viewModel.updateNutrition(calories,carbohydrates, proteins, fats)
        }

        binding.apply {
            labelTextView.text = name
            scoreTextView.text = score.toString()
            caloriesTextView.text = calories.toString()
            carbohydratesTextView2.text = carbohydrates.toString()
            proteinsTextView2.text = proteins.toString()
            fatsTextView2.text = fats.toString()
        }

        supportActionBar?.hide()

    }
}

