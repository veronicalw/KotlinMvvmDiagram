package com.example.kotlinmvvmdiagram.repository

import com.example.kotlinmvvmdiagram.`interface`.ApiInterface

class SalesRepository constructor(private val retrofitService: ApiInterface){
    fun getSalesData() = retrofitService.getSalesData()
}