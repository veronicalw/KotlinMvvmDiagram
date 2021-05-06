package com.example.kotlinmvvmdiagram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmdiagram.repository.CovidRepository
import java.lang.IllegalArgumentException

class CovidViewModelFactory constructor(private val repository: CovidRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  if (modelClass.isAssignableFrom(CovidViewModel::class.java)){
            CovidViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("Covid ViewModel Not Found")
        }
    }
}