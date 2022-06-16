package com.ahmedorabi.weatherapp.features.add_city.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmedorabi.weatherapp.R
import com.ahmedorabi.weatherapp.databinding.FragmentAddCityBinding
import com.ahmedorabi.weatherapp.features.add_city.viewmodel.AddCityViewModel
import com.ahmedorabi.weatherapp.features.add_city.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class AddCityFragment : Fragment() {


    private var _binding: FragmentAddCityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddCityViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddCityBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCitiesResponseFlow()
        observeViewModel()
        binding.addCityBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addCityFragment_to_addCityDialogFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        Timber.e("onResume")
    }

    private fun observeViewModel() {
        sharedViewModel.isInsert.observe(viewLifecycleOwner) {
            Timber.e("Is Insert")
            it?.let {
                if (it) {
                    viewModel.getCitiesResponseFlow()
                    Toast.makeText(requireContext(), "City Added", Toast.LENGTH_LONG).show()
                    sharedViewModel.sendMessage(false)
                }
            }
        }

        viewModel.citiesResponse.observe(viewLifecycleOwner) { userState ->
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