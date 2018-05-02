/*
 * Copyright 2018 Sudhir Khanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sudhirkhanger.genius

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MovieAdapter(private val movies: MutableList<Movie?>,
                   private val onMovieClick: OnMovieClickListener) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object {
        private val TAG = MovieAdapter::class.java.simpleName
    }

    interface OnMovieClickListener {
        operator fun invoke(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view, onMovieClick)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies[position]!!)
    }

    class MovieViewHolder(view: View, private val onMovieClick: OnMovieClickListener) :
            RecyclerView.ViewHolder(view) {
        private val movieImage = view.findViewById<ImageView>(R.id.movie_image_view)

        fun bindMovie(movie: Movie) {
            with(movie) {
                Picasso.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
                        .into(movieImage)
                itemView.setOnClickListener { onMovieClick(this) }
            }
        }
    }
}