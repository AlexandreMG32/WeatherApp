package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.FragmentCityDetailsBinding
import com.example.weatherapp.viewmodels.CityViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CityDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CityDetailsFragment : Fragment() {

    private var _binding : FragmentCityDetailsBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel : CityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            cityName.text = sharedViewModel.getCity().name
            weather.text = sharedViewModel.getCity().weather[0].main
            max.text = sharedViewModel.getCity().main.temp_max.toInt().toString() + "º"
            min.text = sharedViewModel.getCity().main.temp_min.toInt().toString() + "º"
            humidity.text = sharedViewModel.getCity().main.humidity.toString() + "%"
            temperature.text = sharedViewModel.getCity().main.temp.toInt().toString() + "º"
            Glide.with(requireContext())
                .load("https://openweathermap.org/img/w/" + sharedViewModel.getCity().weather[0].icon + ".png")
                .into(icone)
        }
    }


}