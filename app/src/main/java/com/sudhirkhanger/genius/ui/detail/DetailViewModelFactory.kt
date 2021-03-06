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

package com.sudhirkhanger.genius.ui.detail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sudhirkhanger.genius.data.MovieRepository
import com.sudhirkhanger.genius.di.component.Injector

class DetailViewModelFactory(private val id: Int) : ViewModelProvider.Factory {

    private val movieRepository: MovieRepository = Injector.getAppComponent().getMovieRepository()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java))
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(movieRepository, id) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
