package com.example.maxlestagedevmobileproject

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import java.util.*


const val MENU_LIST_LATEST_MOVIES = 1
const val MENU_SEARCH_MOVIE = 2



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menu.add(0, MENU_LIST_LATEST_MOVIES, 0, "List latest movies")
        menu.add(0, MENU_SEARCH_MOVIE, 0, "Search Movie")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MENU_LIST_LATEST_MOVIES) {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)}

        else if (MENU_SEARCH_MOVIE == item.itemId) {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}

