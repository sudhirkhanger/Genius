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

import android.arch.persistence.room.*
import android.os.Parcelable
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesList(

        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @field:SerializedName("results")
        val results: List<MovieEntry?>? = null,

        @field:SerializedName("total_results")
        val totalResults: Int? = null
) : Parcelable

@Parcelize
@Entity(tableName = "movie")
data class MovieEntry(

        @field:SerializedName("overview")
        var overview: String? = null,

        @ColumnInfo(name = "original_language")
        @field:SerializedName("original_language")
        var originalLanguage: String? = null,

        @ColumnInfo(name = "original_title")
        @field:SerializedName("original_title")
        var originalTitle: String? = null,

        @field:SerializedName("video")
        var video: Boolean? = null,

        @field:SerializedName("title")
        var title: String? = null,

        @TypeConverters(ListConverter::class)
        @ColumnInfo(name = "genre_ids")
        @field:SerializedName("genre_ids")
        var genreIds: List<Int?>? = null,

        @ColumnInfo(name = "poster_path")
        @field:SerializedName("poster_path")
        var posterPath: String? = null,

        @ColumnInfo(name = "backdrop_path")
        @field:SerializedName("backdrop_path")
        var backdropPath: String? = null,

        @ColumnInfo(name = "release_date")
        @field:SerializedName("release_date")
        var releaseDate: String? = null,

        @ColumnInfo(name = "vote_average")
        @field:SerializedName("vote_average")
        var voteAverage: Double? = null,

        @field:SerializedName("popularity")
        var popularity: Double? = null,

        @PrimaryKey
        @NonNull
        @field:SerializedName("id")
        var id: Int? = null,

        @field:SerializedName("adult")
        var adult: Boolean? = null,

        @ColumnInfo(name = "vote_count")
        @field:SerializedName("vote_count")
        var voteCount: Int? = null
) : Parcelable {
    @Ignore
    constructor() : this(null, null, null, null, null,
            null, null, null, null, null,
            null, null, null, null)
}