package com.sudhirkhanger.genius.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sudhirkhanger.genius.BuildConfig
import com.sudhirkhanger.genius.data.database.MovieEntry
import com.sudhirkhanger.genius.data.database.MoviesList
import com.sudhirkhanger.genius.data.network.TmdbService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val tmdbService: TmdbService) : ViewModel() {

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
        val call = tmdbService.getPopularMovies(1, BuildConfig.THE_MOVIE_DB_API_KEY)
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
