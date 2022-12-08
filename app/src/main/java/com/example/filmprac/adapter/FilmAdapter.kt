package com.example.filmprac.adapter

import FilmList
import Films
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmprac.R
import com.example.filmprac.View.BaseFragment
import com.example.filmprac.databinding.ItemFilmBinding

class FilmAdapter(private val filmList: List<Films>):RecyclerView.Adapter<FilmAdapter.FilmHolder>() {

    class FilmHolder(val binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(view,parent,false)
        return FilmHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film = filmList[position]
        with(holder.binding){
            tView.text = film.name
        }
        Glide.with(holder.binding.imView).load(film.image_url).into(holder.binding.imView)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

}