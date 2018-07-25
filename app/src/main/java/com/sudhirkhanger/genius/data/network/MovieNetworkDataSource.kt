package com.sudhirkhanger.genius.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.sudhirkhanger.genius.AppExecutors
import com.sudhirkhanger.genius.BuildConfig
import com.sudhirkhanger.genius.data.database.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MovieNetworkDataSource(private val context: Context,
                             private val appExecutors: AppExecutors,
                             private val tmdbService: TmdbService) {

    private var movieListLiveData = MutableLiveData<MoviesList>()

    fun getMovieList(): LiveData<MoviesList> {
        return movieListLiveData
    }

    fun fetchMovieList() {
        val call = tmdbService.getPopularMovies(1, BuildConfig.THE_MOVIE_DB_API_KEY)
        call.enqueue(object : Callback<MoviesList?> {
            override fun onFailure(call: Call<MoviesList?>?, t: Throwable?) {
                Timber.e(t.toString())
            }

            override fun onResponse(call: Call<MoviesList?>?, response: Response<MoviesList?>?) {
                movieListLiveData.postValue(response?.body())
            }
        })
    }
}