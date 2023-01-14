package com.amonteiro.a22_ynova

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maxlestagedevmobileproject.MoviesBean
import com.example.maxlestagedevmobileproject.MoviesListResultBean
import com.squareup.picasso.Picasso

class MovieListAdapter : ListAdapter<MoviesBean, MoviesListResultBean.ViewHolder>(Comparator()) {

    class ViewHolder(val binding: RowMoviesBinding) : RecyclerView.ViewHolder(binding.root)

    class Comparator : DiffUtil.ItemCallback<WeatherBean>() {

        override fun areItemsTheSame(oldItem: WeatherBean, newItem: WeatherBean) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: WeatherBean, newItem: WeatherBean) = oldItem == newItem

    }

    //Création des lignes
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(RowWeatherBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.tvville.text = "${data.name}"
        holder.binding.tvtemp.text = "${data.temperature.temp}° (${data.temperature.temp_min}° / ${data.temperature.temp_max}°)"

        if(data.weather.isNotEmpty()) {
            Picasso.get().load("https://openweathermap.org/img/wn/${data.weather.get(0).icon}@4x.png").into(holder.binding.imageView)
        }
        else {
            holder.binding.imageView.setImageDrawable(null)
        }
    }

}