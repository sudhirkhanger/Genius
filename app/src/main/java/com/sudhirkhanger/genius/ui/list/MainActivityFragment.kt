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
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sudhirkhanger.genius.R
import com.sudhirkhanger.genius.di.component.DaggerMainActivityComponent
import com.sudhirkhanger.genius.di.component.Injector
import com.sudhirkhanger.genius.ui.detail.DetailActivity
import javax.inject.Inject

class MainActivityFragment : Fragment() {

    companion object {
        const val KEY_MOVIE = "movie_parcel"
        private const val COL = 3
    }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        movieAdapter = MovieAdapter {
            val detailActivityIntent = Intent(activity, DetailActivity::class.java)
            detailActivityIntent.putExtra(KEY_MOVIE, it)
            startActivity(detailActivityIntent)
        }

        movieRecyclerView = view.findViewById<RecyclerView>(R.id.movie_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, COL)
            adapter = movieAdapter
        }

        val mainViewModel: MainViewModel by lazy {
            ViewModelProviders.of(this, mainViewModelFactory)
                    .get(MainViewModel::class.java)
        }

        mainViewModel.movieList.observe(this, Observer {
            movieAdapter.setMovieData(it!!.toMutableList())
        })

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        val daggerMainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(Injector.getAppComponent())
                .build()
        daggerMainActivityComponent.inject(this)

        mainViewModelFactory = daggerMainActivityComponent.getMainViewModelFactory()
    }
}
