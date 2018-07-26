package com.sudhirkhanger.genius.data.network

import android.content.Intent
import android.support.v4.app.JobIntentService
import com.sudhirkhanger.genius.AppApplication
import com.sudhirkhanger.genius.AppExecutors
import com.sudhirkhanger.genius.di.component.ApplicationComponent
import timber.log.Timber

class MovieSyncIntentService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        Timber.e("called job intent service")

        val applicationComponent: ApplicationComponent =
                AppApplication.instance.get(this.application).getApplicationComponent()

        MovieNetworkDataSource(
                this.applicationContext,
                AppExecutors(),
                applicationComponent.getMovieService())
                .fetchMovieList()
    }
}