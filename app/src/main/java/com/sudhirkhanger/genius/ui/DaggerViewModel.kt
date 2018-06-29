package com.sudhirkhanger.genius.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sudhirkhanger.genius.BuildConfig
import com.sudhirkhanger.genius.model.Movie
import com.sudhirkhanger.genius.model.MovieList
import com.sudhirkhanger.genius.retrofit.TheMovieDbService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class DaggerViewModel @Inject constructor(private val theMovieDbService: TheMovieDbService) : ViewModel() {

    companion object {
        lateinit var moviesList: MutableLiveData<List<Movie?>>
        private set
    }

    fun getMovies(): LiveData<List<Movie?>> {
        Timber.e("getMovies called")
        moviesList = MutableLiveData()
        loadMovies()
        return moviesList
    }

    private fun loadMovies() {
        val call = theMovieDbService.getPopularMovies(1, BuildConfig.THE_MOVIE_DB_API_KEY)
        call.enqueue(object : Callback<MovieList?> {
            override fun onFailure(call: Call<MovieList?>?, t: Throwable?) {
                Timber.e(t.toString())
            }

            override fun onResponse(call: Call<MovieList?>?, response: Response<MovieList?>?) {
                moviesList.value = response?.body()?.results
            }
        })
    }
}
