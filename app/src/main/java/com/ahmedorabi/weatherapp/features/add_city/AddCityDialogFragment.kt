package com.ahmedorabi.weatherapp.features.add_city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.ahmedorabi.weatherapp.databinding.FragmentAddCityDialogBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddCityDialogFragment : DialogFragment() {

    private var _binding: FragmentAddCityDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddCityViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCityDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addCityBtn.setOnClickListener {
            val city = binding.addCityEd.text.toString()
            viewModel.addCity(city)

        }
    }


    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams = dialog?.window?.attributes!!
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.window?.attributes = params
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}