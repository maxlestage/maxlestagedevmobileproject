package com.amonteiro.a22_ynova

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherAroundViewModel : ViewModel() {

    //Pas forcement besoin d'observable pour le moment
    var list  = MutableLiveData<ArrayList<WeatherBean>>(ArrayList())

    fun loadData(){
        //reset donnée
        list.postValue(ArrayList())

        thread {
            try {
                list.postValue(RequestUtils.loadWeatherAround())
            }
            catch(e:Exception) {
                e.printStackTrace()
            }
        }
    }
}