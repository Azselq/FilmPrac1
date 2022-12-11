package com.example.filmprac.rest


import com.example.filmprac.response.FilmList
import retrofit2.Call
import retrofit2.http.GET

interface FilmApi {
    @GET("films.json")
    fun getResult(): Call<FilmList>

}