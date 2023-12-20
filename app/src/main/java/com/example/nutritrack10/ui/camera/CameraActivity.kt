package com.example.nutritrack10.ui.camera

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {

    private val captureImage = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { result ->
        if (result != null) {
            // Set the result (captured image) and finish the activity
            val intent = Intent()
            intent.putExtra("capturedImage", result)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            // Handle error or cancellation
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content view, initialize camera, or other setup as needed

        // Trigger image capture
        captureImage.launch(null)
    }
}
