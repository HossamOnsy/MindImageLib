package com.sam.mindvalleyimagelib


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.sam.mindvalleyimagelib.di.component.DaggerMainComponent
import com.sam.mindvalleyimagelib.di.modules.NetworkModule
import okhttp3.internal.Internal.instance
//import com.NearHero.di.appComponent
//import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidLogger
//import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class MyApplication : Application() {


    init {
        instance = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
        @SuppressLint("StaticFieldLeak")
        private var instance: MyApplication? = null

        fun applicationContext() : MyApplication {
            return instance as MyApplication
        }
    }
    override fun onCreate() {
        super.onCreate()
        context = this
        val component = DaggerMainComponent.builder().networkModule(NetworkModule(applicationContext())).build()
        component.inject(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }


}