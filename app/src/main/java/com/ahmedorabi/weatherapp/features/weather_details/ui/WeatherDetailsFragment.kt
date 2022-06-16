package com.ahmedorabi.weatherapp.features.weather_details.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmedorabi.weatherapp.core.data.api.Resource
import com.ahmedorabi.weatherapp.core.domain.model.WeatherResponse
import com.ahmedorabi.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.ahmedorabi.weatherapp.features.utils.CITY_MAME_KEY
import com.ahmedorabi.weatherapp.features.weather_details.viewmodel.WeatherDetailsViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {
    private var cityName: String? = null
    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityName = it.getString(CITY_MAME_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCitiesResponseFlow(cityName ?: "")
        observeViewModel()
    }

    private fun observeViewModel() {

        viewModel.citiesResponse.observe(viewLifecycleOwner) { userState ->

            when (userState.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }
                Resource.Status.SUCCESS -> {
                    hideLoading()
                    Timber.e(userState.data.toString())
                    updateUI(userState.data!!)

                }
                Resource.Status.ERROR -> {
                    hideLoading()
                    Toast.makeText(activity, userState.message, Toast.LENGTH_LONG).show()
                }

            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(response: WeatherResponse) {
        val celsius = (response.main.temp - 273.15).toInt()

        val url = "https://openweathermap.org/img/w/${response.weather[0].icon}.png"
        Glide.with(this).load(url).into(binding.weatherIcon)

        binding.titleTV.text = response.name
        binding.descriptionTV.text = response.weather[0].description
        binding.tempTv.text = " $celsius C"
        binding.humidityTV.text = response.main.humidity.toString() + " %"
        binding.winSpeedTV.text = response.wind.speed.toString() + " km/h"

        viewModel.addHistoricalModel(celsius,response.name.lowercase(),response.weather[0].main)


    }

    private fun showLoading() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressbar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}