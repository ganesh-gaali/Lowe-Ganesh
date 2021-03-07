package com.example.lowe.ganesh.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.lowe.ganesh.R
import com.example.lowe.ganesh.databinding.FragmentEnterCityBinding
import com.example.lowe.ganesh.databinding.FragmentWeatherInfoBinding

class FragmentEnterCity : Fragment() {

    lateinit var binding: FragmentEnterCityBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterCityBinding.inflate(inflater)
        registerEvents()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun registerEvents() {
        binding.btnLookup.setOnClickListener {
            val cityName = binding.tieCityName.text.toString()
            if (cityName.isNotEmpty()) {
                val dir = FragmentEnterCityDirections.actionToWeatherInfo(cityName)
                it.findNavController().navigate(dir)
            } else {
                Toast.makeText(activity, "Enter City Name", Toast.LENGTH_LONG).show()
            }
        }
    }
}