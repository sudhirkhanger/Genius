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

package com.sudhirkhanger.genius.ui.list

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudhirkhanger.genius.R
import com.sudhirkhanger.genius.data.database.Movie
import com.sudhirkhanger.genius.util.BindingAdapters
import com.sudhirkhanger.genius.util.Util
import kotlinx.android.synthetic.main.list_item.view.*

class MovieAdapter(private val movieClick: (Int?) -> Unit) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: MutableList<Movie?> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
            MovieViewHolder(parent, movieClick)

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
            holder.bindMovie(movies[position])

    class MovieViewHolder(parent: ViewGroup, private val movieClick: (Int?) -> Unit) :
            RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item, parent, false)) {

        fun bindMovie(movie: Movie?) {
            with(movie) {
                BindingAdapters.loadImage(itemView.movie_image_view,
                        Util.getPosterPath(movie?.posterPath))
                itemView.setOnClickListener { movieClick(this?.id) }
            }
        }
    }

    fun setMovieData(newMovies: MutableList<Movie?>) {
        if (movies.size == 0) {
            movies = newMovies
            notifyDataSetChanged()
        } else {
            val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                        movies[oldItemPosition]?.id == newMovies[newItemPosition]?.id

                override fun getOldListSize(): Int = movies.size

                override fun getNewListSize(): Int = newMovies.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldMovieEntry = movies[oldItemPosition]
                    val newMovieEntry = newMovies[newItemPosition]
                    return newMovieEntry?.id == oldMovieEntry?.id
                            && newMovieEntry?.title.equals(oldMovieEntry?.title)
                }
            })
            movies = newMovies
            result.dispatchUpdatesTo(this)
        }
    }
}