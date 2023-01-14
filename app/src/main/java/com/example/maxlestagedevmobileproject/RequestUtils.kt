package com.example.maxlestagedevmobileproject

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

const val URL_API_YTS = "https://yts.mx/api/v2/list_movies.json"


object RequestUtils {

    val client = OkHttpClient()
    val gson = Gson()

    fun loadMovie(movieName:String): WeatherBean {
        var json = sendGet(URL_API_YTS.format(movieName))
        return gson.fromJson(json, WeatherBean::class.java )
    }


    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder().url(url).build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }

}