package com.example.filmprac.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.filmprac.R
import com.example.filmprac.databinding.ItemFilmBinding
import com.example.filmprac.databinding.ItemGenresBinding
import com.example.filmprac.model.Films
import com.example.filmprac.viewModel.Genre

class GenresAdapter(private val genres: List<Genre>, private val parentLifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<GenresAdapter.FilmGenresHolder>() {
    class FilmGenresHolder(val binding: ItemGenresBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmGenresHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemGenresBinding.inflate(view, parent, false)
        return FilmGenresHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmGenresHolder, position: Int) {
        val genre = genres[position]
        with(holder.binding){
            tvGenres.text = genre.genre
            root2Container.setOnClickListener { genre.onClickGenre() }
        }
        genre.isSelected.observe(
            parentLifecycleOwner
        ) {
            holder.binding.root2Container.setBackgroundColor(
                ContextCompat.getColor(holder.binding.root2Container.context, if(it) R.color.black else R.color.purple_200)
            )
        }


    }

    override fun getItemCount(): Int {
        return genres.size
    }


}