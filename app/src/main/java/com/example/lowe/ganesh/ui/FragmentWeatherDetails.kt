package com.example.lowe.ganesh.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.lowe.ganesh.R
import com.example.lowe.ganesh.databinding.FragmentWeatherDetailsBinding

class FragmentWeatherDetails : Fragment() {

    lateinit var binding: FragmentWeatherDetailsBinding
    val args: FragmentWeatherDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherDetailsBinding.inflate(inflater)
        setUp()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun setUp() {
        args.weatherItem.let {
            (activity as AppCompatActivity).supportActionBar?.title = it.cityName
            binding.tvDetailTempValue.text = it.temperature.toString()
            binding.tvDetailsTempLike.text =
                getString(R.string.str_detail_feels_like, it.feelLike.toString())
            binding.tvDetailsTempLabelValue.text = it.description
        }
    }
}