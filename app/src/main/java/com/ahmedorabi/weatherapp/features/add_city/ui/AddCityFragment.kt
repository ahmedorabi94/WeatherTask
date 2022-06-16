package com.ahmedorabi.weatherapp.features.add_city.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmedorabi.weatherapp.R
import com.ahmedorabi.weatherapp.databinding.FragmentAddCityBinding
import com.ahmedorabi.weatherapp.features.add_city.viewmodel.AddCityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddCityFragment : Fragment() {


    private var _binding: FragmentAddCityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddCityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCityBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.city_label)
        observeViewModel()
        binding.addCityBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addCityFragment_to_addCityDialogFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.allCities.observe(viewLifecycleOwner) { userState ->
            userState?.let {
                val adapter = CityAdapter()
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