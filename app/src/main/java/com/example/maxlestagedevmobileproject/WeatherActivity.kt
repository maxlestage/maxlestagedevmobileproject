package com.amonteiro.a22_ynova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amonteiro.a22_ynova.databinding.ActivityWeatherBinding
import com.squareup.picasso.Picasso

class WeatherActivity : AppCompatActivity() {

    //Instancie le XML à retardement (by lazy) plus exactement à la 1er utilisation
    val binding by lazy { ActivityWeatherBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observe()

        binding.btLoad.setOnClickListener {
            model.loadData("Toulouse")
        }

    }

    fun observe() {
        //Gestion du message d'erreur, déclanché au changement de la valeur model.errorMessage
        //A l'abonnement egalement
        model.errorMessage.observe(this) {
            binding.tvError.text = it
            binding.tvError.isVisible = it.isNotBlank()
        }

        //Gestion de la progressBar
        model.runInProgress.observe(this) {
            binding.progressBar.isVisible = it
        }

        //Gestion de la donnée
        model.weather.observe(this) {

            //Affichage des données version kotlin
            binding.tv.text = it?.name ?: "-"
            binding.tvWind.text = "${it?.wind?.speed ?: "-"}"
            binding.tvMinMax.text = "(${it?.temperature?.temp_min ?: "-"}°/${it?.temperature?.temp_max ?: "-"}°)"

            //Version classique
            if (it != null) {
                binding.tvTemp.text = "${it.temperature?.temp}°"
            }
            else {
                binding.tvTemp.text = "-°"
            }

            //Version avec getOrNull pour eviter l'indexOutOfBoundException (hors de la liste)
            binding.tvDesc.text = it?.weather?.getOrNull(0)?.description ?: "-"

            //Version Kotlin
//                    it?.weather?.get(0)?.icon?.let {
//                        Picasso.get().load("https://openweathermap.org/img/wn/${model.weather?.weather?.get(0)?.icon}@4x.png")
//                            .placeholder(R.mipmap.ic_launcher)
//                            .error(R.drawable.ic_baseline_delete_forever_24)
//                            .into(binding.ivTemp)
//                    } ?: binding.ivTemp.setImageDrawable(null)

            if (!it?.weather.isNullOrEmpty()) {
                Picasso.get().load("https://openweathermap.org/img/wn/${it?.weather?.get(0)?.icon}@4x.png")
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.drawable.ic_baseline_delete_forever_24)
                    .into(binding.ivTemp)
            }
            else {
                binding.ivTemp.setImageDrawable(null)
            }
        }

    }
}

