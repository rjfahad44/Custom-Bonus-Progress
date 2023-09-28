package com.example.custombonusprogress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.custombonusprogress.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {

        lifecycleScope.launch {
            mainViewModel.progress.collectLatest {
                binding.startGuideline.setGuidelinePercent((it / 100))
            }
        }

        lifecycleScope.launch {
            mainViewModel.sellPercentage.collectLatest {
                binding.progressBar.progress = it.toInt()
                binding.totalSoldTextViewAmount.text = "$it%"
            }
        }

        binding.currentProgressEt.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                val percentage = if (it.toString().toFloat() >= 100) 100f else it.toString().toFloat()
                mainViewModel.currentProgress(percentage)
            }else{
                mainViewModel.currentProgress(0f)
            }
        }

        binding.sellPercentageEt.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                val percentage = if (it.toString().toFloat() >= 100) 100f else it.toString().toFloat()
                mainViewModel.sellPercentage(percentage)
            }else{
                mainViewModel.sellPercentage(0f)
            }
        }
    }
}