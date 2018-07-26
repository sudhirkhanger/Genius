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

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sudhirkhanger.genius.BuildConfig
import com.sudhirkhanger.genius.data.database.MovieEntry
import com.sudhirkhanger.genius.data.database.MoviesList
import com.sudhirkhanger.genius.data.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val movieService: MovieService) : ViewModel() {

    companion object {
        lateinit var moviesList: MutableLiveData<List<MovieEntry?>>
        private set
    }

    fun getMovies(): LiveData<List<MovieEntry?>> {
        Timber.e("getMovies called")
        moviesList = MutableLiveData()
        loadMovies()
        return moviesList
    }

    private fun loadMovies() {
        val call = movieService.getPopularMovies(1, BuildConfig.THE_MOVIE_DB_API_KEY)
        call.enqueue(object : Callback<MoviesList?> {
            override fun onFailure(call: Call<MoviesList?>?, t: Throwable?) {
                Timber.e(t.toString())
            }

            override fun onResponse(call: Call<MoviesList?>?, response: Response<MoviesList?>?) {
                moviesList.value = response?.body()?.results
            }
        })
    }
}
