package com.amonteiro.a22_ynova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amonteiro.a22_ynova.databinding.ActivityWeatherAroundBinding

class WeatherAroundActivity : AppCompatActivity() {

    val binding by lazy { ActivityWeatherAroundBinding.inflate(layoutInflater) }
    val model by lazy { ViewModelProvider(this).get(WeatherAroundViewModel::class.java) }

    val adapter = WeatherListAdapter()

    var count = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //reglage RecyclerView
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)

        //Observe
        model.list.observe(this) {
            //On lui donne une copie car si on lui donne la liste du model il ne pourra pas faire la comparaison
            adapter.submitList(it.toList())
        }

        binding.btAdd.setOnClickListener {
            model.loadData()
        }

        binding.btDelete.setOnClickListener {
            model.list.value?.removeFirstOrNull()
            //On déclenche l'observer avec la même donnée
            model.list.postValue(model.list.value)
        }

    }

}