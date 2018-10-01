package com.sudhirkhanger.genius.data.network

import android.content.Intent
import android.support.v4.app.JobIntentService
import com.sudhirkhanger.genius.di.component.Injector
import com.sudhirkhanger.genius.di.scopes.ApplicationScope
import javax.inject.Inject

class MovieSyncIntentService : JobIntentService() {

    @Inject
    lateinit var movieNetworkDataSource: MovieNetworkDataSource

    override fun onHandleWork(intent: Intent) {
        Injector.getAppComponent(applicationContext).inject(this)
        movieNetworkDataSource.fetchMovieList()
    }
}