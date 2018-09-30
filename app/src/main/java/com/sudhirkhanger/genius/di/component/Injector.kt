package com.sudhirkhanger.genius.di.component

import android.content.Context
import com.sudhirkhanger.genius.AppApplication

object Injector {

    fun getAppComponent(context: Context): ApplicationComponent {
        return if (context is AppApplication) {
            context.getApplicationComponent()
        } else {
            throw IllegalArgumentException("Provided context must be of type Application.")
        }
    }
}