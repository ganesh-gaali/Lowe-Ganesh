package com.example.lowe.ganesh.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lowe.ganesh.R
import com.example.lowe.ganesh.databinding.ItemWeatherBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var adapterList = emptyList<WeatherItem>()
    private var callback: WeatherClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(adapterList[position], callback)
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    fun updateData(newList: List<WeatherItem>) {
        this.adapterList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(weatherItem: WeatherItem, callback: WeatherClickListener?) {
            binding.tvInfoLabel.text = weatherItem.label
            binding.tvInfoLabelValue.text =
                binding.root.context.getString(
                    R.string.str_info_temp,
                    weatherItem.temperature.toString()
                )

            binding.root.setOnClickListener {
                callback?.onItemClicked(weatherItem)
            }
        }
    }

    fun setOnClickListener(callback: WeatherClickListener) {
        this.callback = callback
    }

    interface WeatherClickListener {
        fun onItemClicked(weatherItem: WeatherItem)
    }
}