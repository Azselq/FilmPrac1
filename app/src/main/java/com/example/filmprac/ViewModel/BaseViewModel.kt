package com.example.filmprac.ViewModel

import FilmList
import Films
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmprac.rest.FilmRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BaseViewModel : ViewModel() {
    val repository = FilmRepository()
    val FilmListLiveData = MutableLiveData<List<Films>>()


    fun printInformation(){
        repository.getInformation().enqueue(object :Callback<FilmList>{
            override fun onResponse(call:Call<FilmList>, response:Response<FilmList>) {
                val currentResponse = response.body()
                if(currentResponse != null){
                    FilmListLiveData.value = currentResponse.films
                    Log.d("test","не пустой")
                }else{
                    Log.d("test","Пустой")

                }
            }

            override fun onFailure(call:Call<FilmList>, t: Throwable) {
                Log.d("test","Ошибка ", t)
            }
        })


    }


}
