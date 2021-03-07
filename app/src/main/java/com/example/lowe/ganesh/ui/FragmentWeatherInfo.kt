package com.example.lowe.ganesh.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lowe.ganesh.MainViewModel
import com.example.lowe.ganesh.api.ApiHelper
import com.example.lowe.ganesh.api.RetrofitBuilder
import com.example.lowe.ganesh.databinding.FragmentWeatherInfoBinding
import com.example.lowe.ganesh.model.getWeatherItemList
import com.example.lowe.ganesh.utils.Status
import com.example.lowe.ganesh.utils.ViewModelFactory

class FragmentWeatherInfo : Fragment(),WeatherAdapter.WeatherClickListener {

    lateinit var binding: FragmentWeatherInfoBinding
    val args: FragmentWeatherInfoArgs by navArgs()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherInfoBinding.inflate(inflater)
        setupViewModel()
        setupObservers()
        setupRegistration()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupRegistration() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = WeatherAdapter().apply {
                setOnClickListener(this@FragmentWeatherInfo)
            }
        }
    }

    private fun navigateToDetails(item: WeatherItem) {
        val dir = FragmentWeatherInfoDirections.actionToWeatherDetails(item)
        findNavController().navigate(dir)
    }

    private fun setupObservers() {
        args.cityName.let { name ->
            (activity as AppCompatActivity).supportActionBar?.title = name
            viewModel.getCityWeather(name).observe(viewLifecycleOwner, { it ->
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.pbList.visibility = View.GONE
                            resource.data?.let {
                                if (it.cod == 200) {
                                    (binding.rvList.adapter as WeatherAdapter).updateData(it.getWeatherItemList())
                                } else {
                                    Toast.makeText(
                                        activity,
                                        "Error: ${it.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        Status.ERROR -> {
                            binding.pbList.visibility = View.GONE
                        }
                        Status.LOADING -> {
                            binding.pbList.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }

    override fun onItemClicked(weatherItem: WeatherItem) {
        navigateToDetails(weatherItem)
    }
}
