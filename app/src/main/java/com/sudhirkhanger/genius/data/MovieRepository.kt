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

package com.sudhirkhanger.genius.data

import android.arch.lifecycle.LiveData
import com.sudhirkhanger.genius.AppExecutors
import com.sudhirkhanger.genius.data.database.MovieDao
import com.sudhirkhanger.genius.data.database.Movie
import com.sudhirkhanger.genius.data.network.MovieNetworkDataSource
import com.sudhirkhanger.genius.di.scopes.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class MovieRepository @Inject constructor(
        private val movieDao: MovieDao,
        private val movieNetworkDataSource: MovieNetworkDataSource,
        private val executors: AppExecutors) {

    private var isInitialized = false

    init {
        val movieData: LiveData<List<Movie>> = movieNetworkDataSource.getMovieList()
        movieData.observeForever {
            executors.diskIO().execute {
                deleteExistingData()
                movieDao.bulkInsert(*it!!.toTypedArray())
            }
        }
    }

    private fun initializeData() {
        if (isInitialized) return

        isInitialized = true

        movieNetworkDataSource.scheduleRecurringFetchMovieSync()
        executors.diskIO().execute { startFetchMovieService() }
    }

    fun getMovies(): LiveData<List<Movie>> {
        initializeData()
        return movieDao.loadAllMovies()
    }

    fun getMovieById(movieId: Int): LiveData<Movie> {
        initializeData()
        return movieDao.findMovieById(movieId)
    }

    private fun deleteExistingData() = movieDao.deleteOldData()

    private fun startFetchMovieService() = movieNetworkDataSource.startFetchMovieService()
}

