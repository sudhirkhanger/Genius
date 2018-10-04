/*
 * Copyright 2018 Sudhir Khanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sudhirkhanger.genius.data.network

import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService
import com.sudhirkhanger.genius.di.component.Injector
import javax.inject.Inject

class MovieJobService : JobService() {

    @Inject
    lateinit var movieNetworkDataSource: MovieNetworkDataSource

    override fun onStopJob(job: JobParameters?): Boolean {
        Injector.getAppComponent().inject(this)
        movieNetworkDataSource.fetchMovieList()
        jobFinished(job!!, false)
        return true
    }

    override fun onStartJob(job: JobParameters?): Boolean = true
}