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

package com.sudhirkhanger.genius.data.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
        @field:SerializedName("overview") var overview: String? = null,

        @field:SerializedName("title") var title: String? = null,

        @ColumnInfo(name = "poster_path")
        @field:SerializedName("poster_path") var posterPath: String? = null,

        @ColumnInfo(name = "backdrop_path")
        @field:SerializedName("backdrop_path") var backdropPath: String? = null,

        @ColumnInfo(name = "release_date")
        @field:SerializedName("release_date") var releaseDate: String? = null,

        @ColumnInfo(name = "vote_average")
        @field:SerializedName("vote_average") var voteAverage: Double? = null,

        @PrimaryKey
        @NonNull
        @field:SerializedName("id") var id: Int? = null)