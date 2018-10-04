package com.sudhirkhanger.genius.di.component

import com.sudhirkhanger.genius.AppApplication

object Injector {

    @JvmStatic
    fun getAppComponent(): ApplicationComponent =
            AppApplication.instance.getApplicationComponent()
}