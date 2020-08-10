package com.example.asus.week1.utils

import android.view.View
import vinova.key.themoviedb.data.model.Movie


interface onItemClickListener{
    fun onItemClick(item: Movie)
}