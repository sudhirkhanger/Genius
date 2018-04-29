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

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callGetPopularMovies()
    }

    private fun callGetPopularMovies() {
        val theMovieDbService = TheMovieDbService.create()
        val call = theMovieDbService.getPopularMovies(1, BuildConfig.THE_MOVIE_DB_API_KEY)
        call.enqueue(object : Callback<Movie> {

            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                val title = response?.body()?.results!![0]?.title
                Log.e(TAG, title)
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
            }
        })
    }
}
