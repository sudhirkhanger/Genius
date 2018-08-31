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

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.sudhirkhanger.genius.AppApplication
import com.sudhirkhanger.genius.R
import com.sudhirkhanger.genius.databinding.ActivityMainBinding
import com.sudhirkhanger.genius.di.component.ApplicationComponent
import com.sudhirkhanger.genius.di.component.DaggerMainActivityComponent
import com.sudhirkhanger.genius.di.module.MainActivityContextModule
import com.sudhirkhanger.genius.di.qualifier.ActivityContext
import com.sudhirkhanger.genius.ui.detail.DetailActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_MOVIE = "movie_parcel"
        private const val COL = 3
    }

    @Inject
    @field:ActivityContext
    lateinit var activityContext: Context

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)

        val applicationComponent: ApplicationComponent = AppApplication.instance.get(this@MainActivity)
                .getApplicationComponent()

        DaggerMainActivityComponent
                .builder()
                .mainActivityContextModule(MainActivityContextModule(this@MainActivity))
                .applicationComponent(applicationComponent)
                .build()
                .injectMainActivity(this@MainActivity)

        movieAdapter = MovieAdapter {
            val detailActivityIntent = Intent(this@MainActivity,
                    DetailActivity::class.java)
            detailActivityIntent.putExtra(KEY_MOVIE, it)
            startActivity(detailActivityIntent)
        }

        movieRecyclerView = binding.movieRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity, COL)
            adapter = movieAdapter
        }

        val mainViewModel = ViewModelProviders.of(this, mainViewModelFactory)
                .get(MainViewModel::class.java)
        mainViewModel.getMovies().observe(this, Observer {
            movieAdapter.setMovieData(it!!.toMutableList())
        })
    }
}
