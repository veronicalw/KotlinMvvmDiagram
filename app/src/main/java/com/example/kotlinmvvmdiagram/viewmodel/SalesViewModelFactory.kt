package com.example.kotlinmvvmdiagram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmdiagram.repository.SalesRepository
import java.lang.IllegalArgumentException

class SalesViewModelFactory constructor(private val repository: SalesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SalesViewModel::class.java)){
            SalesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("Sales ViewModel Not Found")
        }
    }

}