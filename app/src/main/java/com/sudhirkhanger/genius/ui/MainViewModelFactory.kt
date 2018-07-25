package com.sudhirkhanger.genius.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sudhirkhanger.genius.data.network.TmdbService
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val tmdbService: TmdbService) :
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // https://stackoverflow.com/a/45517555/3034693
        return modelClass.getConstructor(TmdbService::class.java).newInstance(tmdbService)
    }
}