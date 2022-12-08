package com.example.filmprac.rest

import FilmList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmRepository {
    private val filmApi:FilmApi
    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        filmApi = retrofit.create(FilmApi::class.java)
    }

    fun getInformation(): Call<FilmList>{
        return filmApi.getResult()
    }
}