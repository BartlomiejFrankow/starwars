package com.example.movies.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.api.Movie
import com.example.movies.databinding.ItemMovieBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(diffCallback: DiffUtil.ItemCallback<Movie>, val onItemClick: (movie: Movie) -> Unit) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(diffCallback) {

    var adapterItems = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(position)

        if (adapterItems[position].poster != "") loadPicture(
            adapterItems[position].poster!!,
            holder.view.ivMoviePicture,
            holder.view.pbMovie
        )

    }

    override fun submitList(list: MutableList<Movie>?) {
        list?.let {
            this.adapterItems = it.toMutableList()
        }
        super.submitList(list)
    }

    private fun loadPicture(picture: String, imageView: ImageView, progressBar: ProgressBar) {
        Picasso.get().load(picture).into(imageView, object : Callback {
            override fun onError(e: Exception?) {
                progressBar.visibility = View.GONE
            }

            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
        })
    }

    inner class MovieViewHolder(val view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root) {
        private lateinit var movie: Movie

        fun bind(position: Int) {

            this.movie = adapterItems[position]
            view.obj = movie

            view.root.clRoot.setOnClickListener { onItemClick(movie) }

            view.executePendingBindings()
        }
    }

}