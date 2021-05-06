package com.example.kotlinmvvmdiagram

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmdiagram.databinding.ActivityMainBinding
import com.example.kotlinmvvmdiagram.repository.CovidRepository
import com.example.kotlinmvvmdiagram.repository.SalesRepository
import com.example.kotlinmvvmdiagram.retrofit.ApiClient
import com.example.kotlinmvvmdiagram.retrofit.Constant
import com.example.kotlinmvvmdiagram.viewmodel.CovidViewModel
import com.example.kotlinmvvmdiagram.viewmodel.CovidViewModelFactory
import com.example.kotlinmvvmdiagram.viewmodel.SalesViewModel
import com.example.kotlinmvvmdiagram.viewmodel.SalesViewModelFactory
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class MainActivity : AppCompatActivity() {
    private lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart

    lateinit var covidViewModel: CovidViewModel
    lateinit var salesViewModel: SalesViewModel

    lateinit var binding: ActivityMainBinding

    private val covidService = ApiClient.getApiCovid()
    private val salesService = ApiClient.getApiSales()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barChart = findViewById(R.id.BarChart)
        pieChart = findViewById(R.id.PieChart)

        binding = ActivityMainBinding.inflate(layoutInflater)

        covidViewModel = ViewModelProvider(
            this,
            CovidViewModelFactory(CovidRepository(covidService))
        ).get(CovidViewModel::class.java)

        salesViewModel = ViewModelProvider(
            this,
            SalesViewModelFactory(SalesRepository(salesService))
        ).get(SalesViewModel::class.java)

        getPieChart()
        getBarChart()

    }

    private fun getPieChart() {
        covidViewModel.covidList.observe(this, Observer {
            Log.d(Constant.TAG_Covid, "onCreate PieChart: $it")
            if (it != null) {
                val covidEntries: ArrayList<PieEntry> = ArrayList()
                for ((attributes) in it!!) {
                    binding.PieChart.visibility
                    covidEntries.add(PieEntry(attributes.Kasus_Posi, attributes.Provinsi))
                    Log.d("Entries Covid", covidEntries.toString())
                    pieChart.animateXY(5000, 5000)

                    val colors = ArrayList<Int>()
                    for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
                    for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
                    for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
                    for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
                    for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
                    colors.add(ColorTemplate.getHoloBlue())

                    val pieDataSet = PieDataSet(covidEntries, "Jumlah kasus covid per-provinsi")
                    pieDataSet.colors = colors

                    val pieData = PieData(pieDataSet)
                    pieChart.data = pieData

                    val description = Description()
                    description.text = "Kasus Positif Covid-19"
                    pieChart.description = description
                    pieChart.invalidate()
                }
            }
        })
        covidViewModel.errorMessage.observe(this, Observer { })
        covidViewModel.getCovidData()
    }

    private fun getBarChart() {
        salesViewModel.salesList.observe(this, Observer {
            Log.d(Constant.TAG_Sales, "onCreate BarChart: $it")
            if (it != null) {
                val salesEntries: ArrayList<BarEntry> = ArrayList()
                for ((sales) in it!!) {
                    salesEntries.add(BarEntry(sales.year, sales.total_sales))
                    Log.d("Entries Sales", salesEntries.toString())

                    val colors = ArrayList<Int>()
                    for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
                    for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
                    for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
                    for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
                    for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
                    colors.add(ColorTemplate.getHoloBlue())

                    val barDataSet = BarDataSet(salesEntries, "Penjualan")
                    barDataSet.colors = colors

                    val barData = BarData(barDataSet)
                    barData.barWidth = 0.9f

                    barChart.visibility = View.VISIBLE
                    barChart.animateY(5000)
                    barChart.data = barData
                    barChart.setFitBars(true)

                    val description = Description()
                    description.text = "Total Penjualan Produk Apple per-tahun"
                    barChart.description = description
                    barChart.invalidate()
                }
            }
        })
        salesViewModel.errorMessage.observe(this, Observer { })
        salesViewModel.getSalesData()
    }
}