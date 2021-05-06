package com.example.kotlinmvvmdiagram.model

import com.google.gson.annotations.SerializedName

data class Sales(
    @SerializedName("fid") val fid: Int,
    @SerializedName("product") val product: String,
    @SerializedName("month") val month: String,
    @SerializedName("year") val year: Float,
    @SerializedName("total_sales") val total_sales: Float
) {
}