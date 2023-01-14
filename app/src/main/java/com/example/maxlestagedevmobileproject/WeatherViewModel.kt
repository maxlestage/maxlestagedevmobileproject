package com.amonteiro.a22_ynova

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class WeatherViewModel : ViewModel() {

    var weather = MutableLiveData<WeatherBean?>()
    var errorMessage = MutableLiveData("")
    //permet d'indiquer un thread en cours ou non
    var runInProgress = MutableLiveData(false)

    fun loadData(cityName : String){

        //Reset donnée
        errorMessage.postValue("")
        weather.postValue(null)
        runInProgress.postValue(true)

        thread {
            try {
                //Appel on va chercher les donner
                weather.postValue(RequestUtils.loadWeather(cityName))
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue("UNe erreur est survenue : ${e.message}")
            }

            runInProgress.postValue(false)
        }
    }


}