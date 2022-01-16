package com.example.retrofitexamplekotlin.Network

import com.example.retrofitexamplekotlin.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("marvel")
    fun getMovieList(): Call<MutableList<Movie>>
}