package com.example.maxlestagedevmobileproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.maxlestagedevmobileproject.databinding.ActivityMoviesBinding
import com.squareup.picasso.Picasso

class MoviesActivity : AppCompatActivity() {

    //Instancie le XML à retardement (by lazy) plus exactement à la 1er utilisation
    val binding by lazy { ActivityMoviesBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(MoviesViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observe()

        binding.btLoad.setOnClickListener {
            model.loadData("")
        }
    }

    fun observe() {
        //Gestion du message d'erreur, déclanché au changement de la valeur model.errorMessage
        //A l'abonnement egalement
        model.errorMessage.observe(this) {
            binding.tvError.text = it
            binding.tvError.isVisible = it.isNotBlank()
        }
    }
}