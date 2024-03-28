package com.imprarce.android.testtaskdelivery

import android.app.Application
import com.imprarce.android.testtaskdelivery.dependency.DependencyProvider
import com.imprarce.android.testtaskdelivery.dependency.DependencyProviderImpl

class MyApplication : Application() {

    val dependencyProvider: DependencyProvider = DependencyProviderImpl(this)

    override fun onCreate() {
        super.onCreate()
    }

}
