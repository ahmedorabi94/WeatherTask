package com.ahmedorabi.weatherapp.features.historical.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedorabi.weatherapp.R
import com.ahmedorabi.weatherapp.core.domain.model.HistoricalModel
import com.ahmedorabi.weatherapp.databinding.HistoricalItemBinding

class HistoricalAdapter :
    ListAdapter<HistoricalModel, HistoricalAdapter.MyViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<HistoricalModel>() {
        override fun areItemsTheSame(oldItem: HistoricalModel, newItem: HistoricalModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: HistoricalModel,
            newItem: HistoricalModel
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            HistoricalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }


    class MyViewHolder(private var binding: HistoricalItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoricalModel) {
            binding.dateTimeTV.text = item.dateTime
            binding.descTV.text = item.desc
            binding.tempTv.text = item.temp.toString() + "C"


            binding.infoBtn.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("cityName", item.name)
                binding.root.findNavController()
                    .navigate(R.id.action_historicalFragment_to_weatherDetailsFragment, bundle)
            }

        }

    }


}