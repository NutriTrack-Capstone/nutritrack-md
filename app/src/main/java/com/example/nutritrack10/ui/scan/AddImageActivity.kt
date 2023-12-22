package com.example.nutritrack10.ui.scan

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nutritrack10.databinding.ActivityAddImageBinding
import com.example.nutritrack10.ui.scanresult.CameraResultActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class AddImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddImageBinding
    private var currentImageUri: Uri? = null
    private val viewModel by viewModels<AddImageViewModel> {
        AddImageViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        binding.imageView2.setOnClickListener { onBackPressed() }
        binding.btnGallery.setOnClickListener { startGallery() }
        binding.btnCamera.setOnClickListener { checkCameraPermission() }
        binding.buttonAdd.setOnClickListener { uploadImage() }

        viewModel.uploadResponse.observe(this) { response ->
            response?.let {
                if (it.status == "success") {
//                    val hasilScan = it.data
                    val hasilScan = it.data
                    startActivity(
                        Intent(this, CameraResultActivity::class.java).apply {
                            putExtra("name", hasilScan?.foodName)
                            putExtra("score", hasilScan?.score)
                            putExtra("calories", hasilScan?.calories)
                            putExtra("carbohydrates", hasilScan?.carbo)
                            putExtra("proteins", hasilScan?.protein)
                            putExtra("fats", hasilScan?.fat)
                        }
                    )
                    Log.d("UploadResponse", "Upload successful. Food name: ${hasilScan?.foodName}")
                } else {
                    Log.e("UploadResponse", "Upload failed. Status: ${it.status}")
                    Toast.makeText(this, "Upload failed. Status: ${it.status}", Toast.LENGTH_SHORT).show()
                }
                binding.progressBar.visibility = View.GONE
                binding.buttonAdd.isEnabled = true
            }
        }


    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // If permission is not granted, request the permission
            requestCameraPermission()
        } else {
            // If permission is granted, start the camera
            startCamera()
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            REQUEST_CAMERA_PERMISSION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // If permission is granted after request, start the camera
                startCamera()
            } else {
                // If permission is denied, show a message or take appropriate action
                Toast.makeText(
                    this,
                    "Camera permission denied. Cannot open the camera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val file = uriToFile(uri, this)
            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)

            // Tampilkan ProgressBar
            binding.progressBar.visibility = View.VISIBLE
            // Sembunyikan tombol upload
            binding.buttonAdd.isEnabled = false

            viewModel.uploadImage(imagePart)
        }

    }



    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivItemImage.setImageURI(it)
        }
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 123
    }
}
