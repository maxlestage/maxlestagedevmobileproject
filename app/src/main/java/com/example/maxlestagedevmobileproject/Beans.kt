package com.example.maxlestagedevmobileproject

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/* -------------------------------- */
// API YTS.MX
/* -------------------------------- */

data class MovieBean(@SerializedName("main")
                       var name:MovieNameBean,
                       var year:String,
                       var genre : List<GenreBean>, var id:Long )

data class MovieNameBean(var name : String)
data class GenreBean(var genre : String)
data class MoviesListResultBean(var list : ArrayList<MovieBean>)
