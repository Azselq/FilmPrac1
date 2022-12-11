package com.example.filmprac.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmprac.R
import com.example.filmprac.databinding.ItemFilmBinding
import com.example.filmprac.model.Films


class FilmAdapter(private val filmList: List<Films>) :
    RecyclerView.Adapter<FilmAdapter.FilmHolder>() {

    class FilmHolder(val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(view, parent, false)
        return FilmHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film = filmList[position]
        with(holder.binding) {
            tView.text = film.name
        }
        val requestOptions = RequestOptions()
        requestOptions.error(R.drawable.pig)
        Glide.with(holder.binding.imView).setDefaultRequestOptions(requestOptions)
            .load(film.image_url).into(holder.binding.imView)
        holder.itemView.setOnClickListener { film.onClick() }
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

}