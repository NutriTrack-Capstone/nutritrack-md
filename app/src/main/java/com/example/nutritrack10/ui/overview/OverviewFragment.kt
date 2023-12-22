package com.example.nutritrack10.ui.overview

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritrack10.Injection
import com.example.nutritrack10.databinding.FragmentOverviewBinding
import com.example.nutritrack10.data.local.model.Food
import com.example.nutritrack10.ui.scanresult.CameraResultActivity
import com.example.nutritrack10.ui.login.LoginActivity
import com.example.nutritrack10.ui.scan.AddImageActivity
import com.example.nutritrack10.utils.loadImage
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OverviewViewModel by viewModels {
        OverviewViewModelFactory(Injection.provideUserRepository(requireContext()))
    }

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
            val dailyNutrition = it.data
            viewModel.fetchRecommendationData(
                dailyNutrition?.maintainCalories ?: 0,
                dailyNutrition?.dailyCaloriesLeft ?: 0
            )
            binding.tvcountLeftCalories.text =
                "- " + it.data?.dailyCaloriesLeft?.toString() + " kcal"
            binding.tvcountCurrentCalories.text = it.data?.currentCalories?.toString() + " kcal"
            binding.tvcountGoalCalories.text =
                "Target: " + it.data?.maintainCalories?.toString() + " kcal"

            binding.tvcountLeftCarbo.text = "- " + it.data?.dailyCarboLeft?.toString() + " gr"
            binding.tvcountCurrentCarb.text = it.data?.currentCarbo?.toString() + " gr"
            binding.tvcountGoalCarbo.text = "Target: " + it.data?.maintainCarbo?.toString() + " gr"

            binding.tvcountLeftProteins.text = "- " + it.data?.dailyProteinLeft?.toString() + " gr"
            binding.tvcountCurrentProt.text = it.data?.currentProtein?.toString() + " gr"
            binding.tvcountGoalProteins.text =
                "Target: " + it.data?.maintainProtein?.toString() + " gr"

            binding.tvcountLeftFats.text = "- " + it.data?.dailyFatLeft?.toString() + " gr"
            binding.tvcountCurrentFats.text = it.data?.currentFat?.toString() + " gr"
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

        viewModel.recommendationData.observe(requireActivity()) {
            val data = it.data
            binding.tvFoodname.text = data?.recomendation1?.name.orEmpty()
            binding.tvFoodname2.text = data?.recomendation2?.name.orEmpty()
            binding.tvFoodname3.text = data?.recomendation3?.name.orEmpty()
            binding.imgFood.loadImage(data?.recomendation1?.image.orEmpty())
            binding.imgFood2.loadImage(data?.recomendation2?.image.orEmpty())
            binding.imgFood3.loadImage(data?.recomendation3?.image.orEmpty())
            binding.tvFoodcal.text = "${data?.recomendation1?.calories} kkal"
            binding.tvFoodcal2.text = "${data?.recomendation2?.calories} kkal"
            binding.tvFoodcal3.text = "${data?.recomendation3?.calories} kkal"
        }

        viewModel.fetchRecommendationData(20, 100)

        viewModel.recommendationData.observe(requireActivity()) {
            binding.tvFoodname.text = it.data?.recomendation1?.name.orEmpty()
            binding.tvFoodname2.text = it.data?.recomendation2?.name.orEmpty()
            binding.tvFoodname3.text = it.data?.recomendation3?.name.orEmpty()
        }

        binding.cameraFAB.setOnClickListener {
            val addImageIntent = Intent(requireContext(), AddImageActivity::class.java)
            startActivity(addImageIntent)
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



