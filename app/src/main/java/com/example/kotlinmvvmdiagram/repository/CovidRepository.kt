package com.example.kotlinmvvmdiagram.repository

import com.example.kotlinmvvmdiagram.`interface`.ApiInterface

class CovidRepository constructor(private val retrofitService: ApiInterface){
    fun getCovidData() = retrofitService.getCovidData()
}