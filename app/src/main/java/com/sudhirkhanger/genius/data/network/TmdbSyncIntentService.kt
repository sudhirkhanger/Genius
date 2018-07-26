package com.sudhirkhanger.genius.data.network

import android.content.Intent
import android.support.v4.app.JobIntentService
import com.sudhirkhanger.genius.AppApplication
import com.sudhirkhanger.genius.AppExecutors
import com.sudhirkhanger.genius.di.component.ApplicationComponent

class TmdbSyncIntentService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        val applicationComponent: ApplicationComponent = AppApplication.instance.get(this)
                .getApplicationComponent()
        MovieNetworkDataSource(this, AppExecutors(), applicationComponent.getTmdbService()).fetchMovieList()
    }
}