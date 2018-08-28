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
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.sudhirkhanger.genius.R
import com.sudhirkhanger.genius.databinding.ActivityDetailBinding
import com.sudhirkhanger.genius.ui.list.MainActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val movieId = intent.extras.getInt(MainActivity.KEY_MOVIE)

        val detailViewModelFactory = DetailViewModelFactory(movieId)
        val detailViewModel = ViewModelProviders.of(this, detailViewModelFactory).get(
                DetailViewModel::class.java)
        detailViewModel.getMovie().observe(this, Observer {
            binding.titleTextView.text = it?.title
            binding.ratingsTextView.text = it?.voteAverage.toString()
            binding.releaseDateTextView.text = it?.releaseDate
            binding.overviewTextView.text = it?.overview

            // TODO replace picasso load with a utility function
            Picasso.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w185/${it?.posterPath}")
                    .into(binding.posterImageView)

            Picasso.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w300/${it?.backdropPath}")
                    .into(binding.backdropImageView)
        })
    }
}
