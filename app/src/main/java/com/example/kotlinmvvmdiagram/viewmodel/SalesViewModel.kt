package com.example.kotlinmvvmdiagram.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmdiagram.model.SalesWrap
import com.example.kotlinmvvmdiagram.repository.SalesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SalesViewModel constructor(private val repository: SalesRepository) : ViewModel() {
    val salesList = MutableLiveData<List<SalesWrap>>()
    val errorMessage = MutableLiveData<String>()

    fun getSalesData() {
        val response = repository.getSalesData()
        response.enqueue(object : Callback<List<SalesWrap>> {
            override fun onResponse(
                call: Call<List<SalesWrap>>,
                response: Response<List<SalesWrap>>
            ) {
                salesList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<SalesWrap>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}