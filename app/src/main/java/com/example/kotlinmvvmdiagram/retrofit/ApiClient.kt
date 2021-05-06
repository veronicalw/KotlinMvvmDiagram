package com.example.kotlinmvvmdiagram.retrofit

import com.example.kotlinmvvmdiagram.`interface`.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var retrofitService: ApiInterface? = null
    var retrofitServices: ApiInterface? = null
    const val BASE_URL = "https://api.kawalcorona.com/indonesia/"
    const val BASE_URL_SALES = "https://mocki.io/"

    fun getApiCovid() : ApiInterface {
        if (retrofitService == null){
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(ApiInterface::class.java)
        }
        return retrofitService!!
    }

    fun getApiSales() : ApiInterface {
        if (retrofitServices == null){
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_SALES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitServices = retrofit.create(ApiInterface::class.java)
        }
        return retrofitServices!!
    }
}