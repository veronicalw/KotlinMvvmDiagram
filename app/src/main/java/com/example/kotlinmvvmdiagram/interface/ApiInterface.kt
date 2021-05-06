package com.example.kotlinmvvmdiagram.`interface`

import com.example.kotlinmvvmdiagram.model.CovidWrap
import com.example.kotlinmvvmdiagram.model.SalesWrap
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("provinsi")
    fun getCovidData(): Call<List<CovidWrap>>

    @GET("v1/43062e4b-7bab-4499-81e5-c315dc013954")
    fun getSalesData(): Call<List<SalesWrap>>
}