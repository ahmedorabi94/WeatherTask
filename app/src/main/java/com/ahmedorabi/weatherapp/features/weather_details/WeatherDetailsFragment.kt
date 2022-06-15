package com.ahmedorabi.weatherapp.features.weather_details

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
            cityName = it.getString("cityName")
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
        //initUI()
        viewModel.getRatesResponseFlow("london")
        observeViewModel()


    }

    private fun observeViewModel() {

        viewModel.ratesResponse.observe(viewLifecycleOwner) { userState ->

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

    private fun updateUI(response: WeatherResponse) {
        binding.titleTV.text = "London"
        val url = "https://openweathermap.org/img/w/${response.weather[0].icon}.png"
        Glide.with(this).load(url).into(binding.weatherIcon)
        binding.descriptionTV.text = "Description : " + response.weather[0].description
        binding.tempTv.text = "Temp : " + response.main.temp.toString()
        binding.humidityTV.text = "Humidity : " + response.main.humidity.toString()
        binding.winSpeedTV.text = "WindsSpeed : " + response.wind.speed.toString()

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