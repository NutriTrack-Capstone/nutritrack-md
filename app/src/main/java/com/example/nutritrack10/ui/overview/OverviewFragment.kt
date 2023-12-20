package com.example.nutritrack10.ui.overview

import android.R
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritrack10.Injection
import com.example.nutritrack10.databinding.FragmentOverviewBinding
import com.example.nutritrack10.data.local.model.Food
import com.example.nutritrack10.ui.camera.CameraActivity
import com.example.nutritrack10.ui.camera.CameraResultActivity
import com.example.nutritrack10.ui.login.LoginActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date


class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private val dataList = ArrayList<Food>()
    private val viewModel:OverviewViewModel by viewModels{
        OverviewViewModelFactory(Injection.provideUserRepository(requireContext()))
    }

    private val REQUEST_CAMERA = 101
    private val REQUEST_CAMERA_PERMISSION = 102

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val calendar = Calendar.getInstance()
        val days = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        val month = arrayOf(
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec"
        )
        val temp = SimpleDateFormat("dd").format(Date()).toString()
        val dateNow = if (temp.toInt() < 10) {
            temp.substring(1)
        } else {
            temp
        }
        val monthNow = month[SimpleDateFormat("MM").format(Date()).toString().toInt() - 1]
        val currentDates =
            days[calendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + dateNow + " " + monthNow
        binding.tvDate.text = currentDates
        viewModel.response.observe(requireActivity()) {
//            if (it.message == "Token invalid"){
//                startActivity(Intent(requireContext(), LoginActivity::class.java))
//                requireActivity().finish()
//            }
            binding.tvcountLeftCalories.text =
                "- " + it.data?.dailyCaloriesLeft?.toString() + " kcal"
            binding.tvcountGoalCalories.text =
                "Target: " + it.data?.maintainCalories?.toString() + " kcal"
            binding.tvcountLeftCarbo.text = "- " + it.data?.dailyCarboLeft?.toString() + " gr"
            binding.tvcountGoalCarbo.text = "Target: " + it.data?.maintainCarbo?.toString() + " gr"
            binding.tvcountLeftProteins.text = "- " + it.data?.dailyProteinLeft?.toString() + " gr"
            binding.tvcountGoalProteins.text =
                "Target: " + it.data?.maintainProtein?.toString() + " gr"
            binding.tvcountLeftFats.text = "- " + it.data?.dailyFatLeft?.toString() + " gr"
            binding.tvcountGoalFats.text = "Target: " + it.data?.maintainFat?.toString() + " gr"
        }
        viewModel.currentDate.observe(requireActivity()) {
            if (currentDates != it) {
                viewModel.setCurrentDate(currentDates)
                viewModel.resetDailyNutrition()
            }
        }

        viewModel.loginResponse.observe(requireActivity()) {
            if (it.message == "Token invalid") {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish()
            }
        }

//        val maintenanceCalor = binding.
//
//        viewModel.recomendation1.observe(requireActivity()){
//            viewModel.getRecommendation1()
//        }


        // Inisialisasi RecyclerView pakai data binding
//        binding.rvFoodrecommendation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//
//        dataList.add(Food("Food 1", "100", R.drawable.alert_light_frame))
//        dataList.add(Food("Food 2", "150", R.drawable.star_on))
//        dataList.add(Food("Food 3", "200", R.drawable.screen_background_dark))
//
//        adapter = FoodAdapter(dataList)
//        binding.rvFoodrecommendation.adapter = adapter


        binding.cameraFAB.setOnClickListener {
            if (hasCameraPermission()) {
                startCamera()
            } else {
                requestCameraPermission()
            }
        }

        return root
    }

//Di Android 6.0 (API level 23) dan versi di atasnya, izin tertentu, termasuk CAMERA, harus diminta secara dinamis selama runtime.
    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.CAMERA),
            REQUEST_CAMERA_PERMISSION
        )
    }

    private fun startCamera() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        startActivityForResult(intent, REQUEST_CAMERA)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with camera operation
                startCamera()
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
                Toast.makeText(
                    requireContext(),
                    "Camera permission denied. Cannot open the camera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

//    untuk membuka capturedimage di CameraResultActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            val capturedImage = data?.getParcelableExtra<Bitmap>("capturedImage")
            viewModel.processCapturedImage(capturedImage)

            // Start CameraResultActivity
            val cameraResultIntent = Intent(requireContext(), CameraResultActivity::class.java)
            startActivity(cameraResultIntent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
