package com.example.custombonusprogress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var _progress = MutableStateFlow(0.0f)
    val progress = _progress

    fun currentProgress(currentPercentage: Float) = viewModelScope.launch{
        _progress.value = currentPercentage
    }

    private var _sellPercentage = MutableStateFlow(0.0f)
    val sellPercentage = _sellPercentage

    fun sellPercentage(currentPercentage: Float) = viewModelScope.launch{
        _sellPercentage.value = currentPercentage
    }

}