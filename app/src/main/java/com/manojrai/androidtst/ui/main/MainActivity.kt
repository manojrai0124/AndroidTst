package com.manojrai.androidtst.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.manojrai.androidtst.R
import com.manojrai.androidtst.ui.forecast.DailyForecastActivity
import com.manojrai.androidtst.utils.display.showToast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var currentLocation: Location? = null
    private val LOCATION_PERMISSION = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLocation()

        btnGetWeather.setOnClickListener {
            getLocation()
        }

        card.setOnClickListener {
            currentLocation?.let { location ->
                Intent(this, DailyForecastActivity::class.java).apply {
                    putExtra("lat", location.latitude.toString())
                    putExtra("lon", location.longitude.toString())
                    startActivity(this)
                }
                return@setOnClickListener
            }
            showToast("Please get weather first!")
        }

        viewModel.weather.observe(this, {
            tvTemp.text = it.main.temp.toString()
            tvFeelsLike.text = it.main.feelsLike.toString()
            tvMinTemp.text = it.main.tempMin.toString()
            tvMaxTemp.text = it.main.tempMax.toString()
            tvPressure.text = it.main.pressure.toString()
            tvHumidity.text = it.main.humidity.toString()
            tvCity.text = it.cityName
        })

        viewModel.progressbar.observe(this, {
            progressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun initLocation() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 60000
        locationCallback = object : LocationCallback() {
            override fun onLocationAvailability(p0: LocationAvailability?) {
                super.onLocationAvailability(p0)
            }

            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
            }
        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            locationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                this.mainLooper
            )
            locationProviderClient.lastLocation.addOnSuccessListener {
                it?.apply {
                    currentLocation = this
                    viewModel.getWeatherMain(this.latitude, this.longitude)
                    Log.e("TAG", "getLocation: Success ${this.latitude}  ${this.longitude}")
                    return@addOnSuccessListener
                }
                showToast("Not getting location")
            }

            locationProviderClient.lastLocation.addOnFailureListener {
                Log.e("TAG", "getLocation: Error ${it.message}")
            }
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                showToast("Location Permission Needed")
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), LOCATION_PERMISSION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        getLocation()
    }

    override fun onDestroy() {
        locationProviderClient.removeLocationUpdates(locationCallback)
        super.onDestroy()
    }
}