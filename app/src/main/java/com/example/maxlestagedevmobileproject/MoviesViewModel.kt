package com.example.maxlestagedevmobileproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class MoviesViewModel: ViewModel() {

    var movie = MutableLiveData<MovieBean?>()
    var errorMessage = MutableLiveData("")

    //permet d'indiquer un thread en cours ou non
    var runInProgress = MutableLiveData(false)

    fun loadData(movieName : String){

        //Reset donnée
        errorMessage.postValue("")
        movie.postValue(null)
        runInProgress.postValue(true)

        thread {
            try {
                //Appel on va chercher les donner
                movie.postValue(RequestUtils.loadMovie(movieName))
            }
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue("UNe erreur est survenue : ${e.message}")
            }

            runInProgress.postValue(false)
        }
    }

}
