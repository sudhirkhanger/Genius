package com.sudhirkhanger.genius.data.network

import android.content.Intent
import android.support.v4.app.JobIntentService
import com.sudhirkhanger.genius.AppApplication
import timber.log.Timber

class MovieSyncIntentService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        Timber.e("called job intent service")

        AppApplication.instance.getApplicationComponent().getNetworkDataSource().fetchMovieList()
    }
}