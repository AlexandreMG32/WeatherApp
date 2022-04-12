package com.example.weatherapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.CityViewModel
import com.example.weatherapp.ListCitiesFragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentListCitiesBinding
import com.example.weatherapp.model.WeatherCard
import com.google.android.material.card.MaterialCardView
import retrofit2.Response

class CityAdapter(private val sharedViewModel : CityViewModel,private val context: Context, private val cities: List<Response<WeatherCard>>?) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        val cityName : TextView = view.findViewById(R.id.city_name)
        val degrees : TextView = view.findViewById(R.id.degrees)
        val icon : ImageView = view.findViewById(R.id.icon)
        val weather : TextView = view.findViewById(R.id.weather)
        val card : MaterialCardView = view.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val adapterLayout = LayoutInflater.from(context).inflate(R.layout.city_item, parent, false)
        return CityViewHolder(adapterLayout);
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val item = cities!!.get(position).body()!!
        holder.cityName.text = item.name;
        holder.degrees.text = "${item.main.temp.toInt()}º"
        holder.weather.text = item.weather[0].main
        Glide.with(holder.icon.context)
            .load("https://openweathermap.org/img/w/" + item.weather[0].icon + ".png")
            .into(holder.icon)
        holder.card.setOnClickListener {
            sharedViewModel.setCity(item)
            Navigation.findNavController(it).navigate(R.id.navigateListToDetails);
        }
    }

    override fun getItemCount(): Int {
        return cities!!.size
    }
}