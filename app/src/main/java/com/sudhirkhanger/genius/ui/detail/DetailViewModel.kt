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

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.sudhirkhanger.genius.data.MovieRepository
import com.sudhirkhanger.genius.data.database.MovieEntry
import javax.inject.Inject

class DetailViewModel @Inject constructor(movieRepository: MovieRepository, id: Int) : ViewModel() {

    val movie: LiveData<MovieEntry> = movieRepository.getMovieById(id)
}