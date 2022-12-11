package com.example.filmprac.viewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmprac.model.Films
import com.example.filmprac.response.FilmList
import com.example.filmprac.rest.FilmRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BaseViewModel : ViewModel() {

    val openDetailFilm = MutableSharedFlow<OpenDetailFilm>()
    val repository = FilmRepository()
    val filmListLiveData = MutableLiveData<List<Films>>()

    fun printInformation() {
        repository.getInformation().enqueue(object : Callback<FilmList> {
            override fun onResponse(call: Call<FilmList>, response: Response<FilmList>) {
                val currentResponse = response.body()
                if (currentResponse != null) {
                    currentResponse.films.forEach {
                        it.onClick = { onItemClick(it) }
                        filmListLiveData.value = currentResponse.films
                    }
                    Log.d("test", "не пустой")
                } else {
                    Log.d("test", "Пустой")

                }
            }

            override fun onFailure(call: Call<FilmList>, t: Throwable) {
                Log.d("test", "Ошибка ", t)
            }

            private fun onItemClick(films: Films) {
                viewModelScope.launch {
                    openDetailFilm.emit(OpenDetailFilm.OpenNewFragment(films))
                }
            }
        })
    }
}


sealed class OpenDetailFilm {
    data class OpenNewFragment(val films: Films) : OpenDetailFilm()
}
