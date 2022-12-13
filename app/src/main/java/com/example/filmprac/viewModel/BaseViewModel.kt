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

    val genresLiveData = MutableLiveData<List<Genre>>()
    var genresList = mutableListOf<String>()
    private val selectedGenre = mutableListOf<String>()

    init {
        kek()
    }

    fun kek() {
        printInformation()
    }

    fun printInformation() {
        repository.getInformation().enqueue(object : Callback<FilmList> {
            override fun onResponse(call: Call<FilmList>, response: Response<FilmList>) {
                val currentResponse = response.body()
                if (currentResponse != null) {
                    currentResponse.films.forEach {
                        it.onClick = { onItemClick(it) }

                        it.genres.forEach {
                            if (it !in genresList) {
                                genresList.add(it)
                            }
                        }


                    }
                    if (genresLiveData.value.isNullOrEmpty()) {
                        val setOfGenre = genresList.map { it }.toSet().toMutableList()
                        genresLiveData.value = setOfGenre.map {
                            val isSelected = MutableLiveData(false)
                            Genre(
                                genre = it,
                                isSelected = isSelected,
                                onClickGenre = {
                                    if (selectedGenre.contains(it)) {
                                        selectedGenre.remove(it)
                                        isSelected.value = false
                                    } else {
                                        selectedGenre.add(it)
                                        isSelected.value = true
                                    }
                                    kek()
                                }
                            )

                        }
                    }
                    var changee2 = selectedGenre
                    Log.d("test", "Выбранно $changee2")
                    Log.d("test", "Все $genresList")
                    var change = currentResponse.films.filter {
                        if (selectedGenre.isNotEmpty()) {
                            it.genres.toString().contains(selectedGenre.joinToString())
                        } else {
                            true
                        }
                    }
                    filmListLiveData.value = change



                    Log.d("test", "не пустой")

                } else {
                    Log.d("test", "Пустой")

                }

            }


            override fun onFailure(call: Call<FilmList>, t: Throwable) {
                Log.d("test", "Ошибка ", t)
            }

        })
    }


    private fun onItemClick(films: Films) {
        viewModelScope.launch {
            openDetailFilm.emit(OpenDetailFilm.OpenNewFragment(films))
        }
    }
}


sealed class OpenDetailFilm {
    data class OpenNewFragment(val films: Films) : OpenDetailFilm()
}

data class Genre(
    val genre: String,
    val isSelected: MutableLiveData<Boolean>,
    var onClickGenre: () -> Unit = {}
)
