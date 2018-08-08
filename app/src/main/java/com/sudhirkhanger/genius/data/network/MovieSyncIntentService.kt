package com.sudhirkhanger.genius.data.network

import android.content.Intent
import android.support.v4.app.JobIntentService
import com.sudhirkhanger.genius.AppApplication

class MovieSyncIntentService : JobIntentService() {

    override fun onHandleWork(intent: Intent) =
            AppApplication.instance.getApplicationComponent().getNetworkDataSource().fetchMovieList()
}