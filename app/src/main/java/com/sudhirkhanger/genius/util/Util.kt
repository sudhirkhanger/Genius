package com.sudhirkhanger.genius.util


object Util {

    fun getPosterPath(path: String?) = "https://image.tmdb.org/t/p/w185/$path"

    fun getBackdropPath(path: String?) = "https://image.tmdb.org/t/p/w300$path"
}