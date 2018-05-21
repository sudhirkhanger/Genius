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

package com.sudhirkhanger.genius.di.component

import android.content.Context
import com.sudhirkhanger.genius.AppApplication
import com.sudhirkhanger.genius.di.module.ContextModule
import com.sudhirkhanger.genius.di.module.RetrofitModule
import com.sudhirkhanger.genius.di.qualifier.ApplicationContext
import com.sudhirkhanger.genius.di.scopes.ApplicationScope
import com.sudhirkhanger.genius.retrofit.TheMovieDbService
import dagger.Component

@ApplicationScope
@Component(modules = [ContextModule::class, RetrofitModule::class])
interface ApplicationComponent {

    fun getApiInterface(): TheMovieDbService

    @ApplicationContext
    fun getContext(): Context

    fun injectApplication(appApplication: AppApplication)
}