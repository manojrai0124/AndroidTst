package com.manojrai.androidtst.ui.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.manojrai.androidtst.R
import com.manojrai.androidtst.utils.display.showToast
import kotlinx.android.synthetic.main.activity_daily_forecast.*
import org.koin.android.ext.android.inject

class DailyForecastActivity : AppCompatActivity() {

    private val viewModel: DailyForeCastViewModel by inject()

    private val forecastAdapter = ForecastAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_forecast)


        rvDaily.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = forecastAdapter
        }

        viewModel.messageString.observe(this, {
            showToast(it)
        })

        viewModel.data.observe(this, {
            forecastAdapter.setList(it)
        })

        viewModel.progress.observe(this, {
            progressbar.visibility = if (it) View.VISIBLE else View.GONE
        })

        val lat = intent.getStringExtra("lat")
        val lon = intent.getStringExtra("lon")
        if (lat != null && lon != null) {
            viewModel.getDailyForeCast(lat, lon)
        }

    }
}