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

package com.sudhirkhanger.genius.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.sudhirkhanger.genius.R
import com.sudhirkhanger.genius.ui.list.MainActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movieId = intent.extras.getInt(MainActivity.KEY_MOVIE)

        val title = findViewById<TextView>(R.id.title_text_view)
        val rating = findViewById<TextView>(R.id.ratings_text_view)
        val releaseDate = findViewById<TextView>(R.id.release_date_text_view)
        val overview = findViewById<TextView>(R.id.overview_text_view)
        val posterImageView = findViewById<ImageView>(R.id.poster_image_view)
        val backdropImageView = findViewById<ImageView>(R.id.backdrop_image_view)

        val detailViewModelFactory = DetailViewModelFactory(movieId)
        val detailViewModel = ViewModelProviders.of(this, detailViewModelFactory).get(
                DetailViewModel::class.java)
        detailViewModel.getMovie().observe(this, Observer {
            title.text = it?.title
            rating.text = it?.voteAverage.toString()
            releaseDate.text = it?.releaseDate
            overview.text = it?.overview

            // TODO replace picasso load with a utility function
            Picasso.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w185/${it?.posterPath}")
                    .into(posterImageView)

            Picasso.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w300/${it?.backdropPath}")
                    .into(backdropImageView)
        })
    }
}
