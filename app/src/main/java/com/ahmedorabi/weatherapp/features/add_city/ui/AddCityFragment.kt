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
import timber.log.Timber


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
        Timber.e("onCreateView")
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initUI()
        Timber.e("onViewCreated")
        viewModel.getRatesResponseFlow()
        observeViewModel()

        binding.addCityBtn.setOnClickListener{
            findNavController().navigate(R.id.action_addCityFragment_to_addCityDialogFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        Timber.e("onResume")
    }

    private fun observeViewModel() {

        ///lifecycleScope.launch {
        viewModel.ratesResponse.observe(viewLifecycleOwner) { userState ->

            userState?.let {
                Timber.e(userState.toString())
                val  adapter = CityAdapter()
                binding.recyclerViewMain.adapter = adapter

                adapter.submitList(userState)
            }



//            when (userState.status) {
//                Resource.Status.LOADING -> {
//                    // showLoading()
//                }
//                Resource.Status.SUCCESS -> {
//                    //  hideLoading()
//
//                    //  viewModel.getPopularList(userState.data!!.rates)
//                    //  setAdapters(userState.data)
//
//                }
//                Resource.Status.ERROR -> {
//                    //   hideLoading()
//                    Toast.makeText(activity, userState.message, Toast.LENGTH_LONG).show()
//                }
//
//            }
            ///   }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}