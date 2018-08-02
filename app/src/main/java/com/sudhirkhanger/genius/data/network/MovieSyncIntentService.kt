package com.sudhirkhanger.genius.data.network

import android.content.Intent
import android.support.v4.app.JobIntentService
import com.sudhirkhanger.genius.di.qualifier.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class MovieSyncIntentService : JobIntentService() {

    @Inject
    @field:ApplicationContext
    lateinit var movieNetworkDataSource: MovieNetworkDataSource

    override fun onHandleWork(intent: Intent) {
        Timber.e("called job intent service")

        movieNetworkDataSource.fetchMovieList()
    }
}