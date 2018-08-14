package com.sudhirkhanger.genius.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import com.firebase.jobdispatcher.*
import com.sudhirkhanger.genius.AppExecutors
import com.sudhirkhanger.genius.BuildConfig
import com.sudhirkhanger.genius.data.database.MoviesList
import com.sudhirkhanger.genius.di.qualifier.ApplicationContext
import com.sudhirkhanger.genius.di.scopes.ApplicationScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ApplicationScope
class MovieNetworkDataSource @Inject constructor(
        @ApplicationContext
        private val context: Context,
        private val appExecutors: AppExecutors,
        private val movieService: MovieService) {

    companion object {
        private const val MOVIE_SYNC_TAG = "movie_sync"
        private const val SYNC_INTERVAL_HOURS = 24
        private val SYNC_INTERVAL_SECONDS = TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS.toLong()).toInt()
        private val SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3
    }

    private val movieListLiveData = MutableLiveData<MoviesList>()

    fun getMovieList(): LiveData<MoviesList> = movieListLiveData

    fun startFetchMovieService() {
        Timber.e("calling intent service")
        val intent = Intent(context, MovieSyncIntentService::class.java)
        JobIntentService
                .enqueueWork(context, MovieSyncIntentService::class.java, 1000, intent)
    }

    fun scheduleRecurringFetchMovieSync() {
        val driver = GooglePlayDriver(context)
        val dispatcher = FirebaseJobDispatcher(driver)

        val syncMovieJob = dispatcher.newJobBuilder()
                .setService(MovieJobService::class.java)
                .setTag(MOVIE_SYNC_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_SECONDS,
                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build()

        dispatcher.schedule(syncMovieJob)
    }

    fun fetchMovieList() {
        Timber.e("Running network call")
        appExecutors.networkIO().execute {
            val call = movieService.getPopularMovies(1, BuildConfig.THE_MOVIE_DB_API_KEY)
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
}