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

package com.sudhirkhanger.genius.di.module

import com.google.gson.Gson
import com.sudhirkhanger.genius.BuildConfig
import com.sudhirkhanger.genius.data.network.MovieService
import com.sudhirkhanger.genius.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun getMovieService(retroFit: Retrofit): MovieService =
            retroFit.create(MovieService::class.java)

    @Provides
    @ApplicationScope
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Provides
    @ApplicationScope
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(addApiKeyInterceptor(BuildConfig.THE_MOVIE_DB_API_KEY))
                    .build()

    @Provides
    @ApplicationScope
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @ApplicationScope
    fun addApiKeyInterceptor(apiKey: String): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()
            val newRequest = request.newBuilder()
                    .url(url)
                    .build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @ApplicationScope
    fun getGson(): Gson = Gson()
}