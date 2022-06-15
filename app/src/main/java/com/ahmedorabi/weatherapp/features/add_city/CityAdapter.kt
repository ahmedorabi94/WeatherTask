package com.ahmedorabi.weatherapp.features.add_city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedorabi.weatherapp.R
import com.ahmedorabi.weatherapp.core.domain.model.City
import com.ahmedorabi.weatherapp.databinding.CityItemBinding

class CityAdapter :
    ListAdapter<City, CityAdapter.MyViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: City,
            newItem: City
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }


    class MyViewHolder(private var binding: CityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: City) {
            binding.cityNameTV.text = item.name


            binding.cityNameTV.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("cityName", binding.cityNameTV.text.toString())
                binding.root.findNavController()
                    .navigate(R.id.action_addCityFragment_to_weatherDetailsFragment, bundle)
            }

            binding.infoBtn.setOnClickListener {
                binding.root.findNavController()
                    .navigate(R.id.action_addCityFragment_to_historicalFragment)
            }

        }

    }


}