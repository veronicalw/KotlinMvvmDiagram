package com.example.kotlinmvvmdiagram.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmdiagram.model.CovidWrap
import com.example.kotlinmvvmdiagram.repository.CovidRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CovidViewModel constructor(private val repository: CovidRepository) : ViewModel() {
    val covidList = MutableLiveData<List<CovidWrap>>()
    val errorMessage = MutableLiveData<String>()

    fun getCovidData() {
        val response = repository.getCovidData()
        response.enqueue(object : Callback<List<CovidWrap>> {
            override fun onResponse(
                call: Call<List<CovidWrap>>,
                response: Response<List<CovidWrap>>
            ) {
                covidList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CovidWrap>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}

