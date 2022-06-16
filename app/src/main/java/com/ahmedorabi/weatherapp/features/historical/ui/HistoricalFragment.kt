package com.ahmedorabi.weatherapp.features.historical.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmedorabi.weatherapp.databinding.FragmentHistoricalBinding
import com.ahmedorabi.weatherapp.features.historical.viewmodel.HistoricalViewModel
import com.ahmedorabi.weatherapp.features.utils.CITY_MAME_KEY
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HistoricalFragment : Fragment() {

    private var cityName: String? = null
    private var _binding: FragmentHistoricalBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoricalViewModel by viewModels()


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

        _binding = FragmentHistoricalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "$cityName Historical"
        viewModel.getCitiesResponseFlow(cityName?.lowercase() ?: "")
        observeViewModel()


    }

    private fun observeViewModel() {
        viewModel.citiesResponse.observe(viewLifecycleOwner) { userState ->

            userState?.let {
                Timber.e(userState.toString())
                val adapter = HistoricalAdapter()
                binding.recyclerViewMain.adapter = adapter
                adapter.submitList(userState)
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}