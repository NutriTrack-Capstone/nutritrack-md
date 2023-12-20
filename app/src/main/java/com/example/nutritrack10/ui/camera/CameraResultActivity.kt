package com.example.nutritrack10.ui.camera

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.nutritrack10.Injection
import com.example.nutritrack10.R
import com.example.nutritrack10.databinding.ActivityCameraResultBinding
import com.example.nutritrack10.ui.overview.OverviewViewModel
import com.example.nutritrack10.ui.overview.OverviewViewModelFactory

class CameraResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraResultBinding
    private lateinit var viewModel: OverviewViewModel
//    private val viewModel:OverviewViewModel by viewModels{
//        OverviewViewModelFactory(Injection.provideUserRepository(requireContext()))
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)



        // Get the captured image from OverviewViewModel
        val capturedImage = viewModel.capturedImage.value

        // Perform further processing or display the image in your UI
        // For example, set it to an ImageView
        binding.imageView.setImageBitmap(capturedImage)
    }
}

