package com.example.filmprac.rest

import FilmList
import retrofit2.Call
import retrofit2.http.GET

interface FilmApi {
    @GET("films.json")
    fun getResult(): Call<FilmList>

}