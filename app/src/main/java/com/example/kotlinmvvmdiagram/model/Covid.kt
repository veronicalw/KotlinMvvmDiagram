package com.example.kotlinmvvmdiagram.model

import com.google.gson.annotations.SerializedName

data class Covid(
    @SerializedName("FID") val FID: Int,
    @SerializedName("Kode_Provi") val Kode_Provin: Int,
    @SerializedName("Provinsi") val Provinsi: String,
    @SerializedName("Kasus_Posi") val Kasus_Posi: Float,
    @SerializedName("Kasus_Semb") val Kasus_Semb: Float,
    @SerializedName("Kasus_Meni") val Kasus_Meni: Float
) {
}