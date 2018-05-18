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

package com.sudhirkhanger.genius

import android.app.Activity
import android.app.Application
import com.sudhirkhanger.genius.di.component.ApplicationComponent
import com.sudhirkhanger.genius.di.component.DaggerApplicationComponent
import com.sudhirkhanger.genius.di.module.ContextModule
import timber.log.Timber

class AppApplication : Application() {

    companion object {
        lateinit var instance: AppApplication
            private set
    }

    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .contextModule(ContextModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.injectApplication(this)
        instance = this

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    fun getApplicationComponent(): ApplicationComponent = appComponent

    fun get(activity: Activity): AppApplication =
            activity.application as AppApplication
}