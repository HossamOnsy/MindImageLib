package com.sam.mindvalleyimagelib.base

import com.sam.mindvalleyimagelib.MyApplication
import com.sam.mindvalleyimagelib.di.component.DaggerMainComponent
import com.sam.mindvalleyimagelib.di.modules.NetworkModule
import com.sam.mindvalleyimagelib.network.RestApi
import javax.inject.Inject

abstract class BaseRepoistory() {

    @Inject
    lateinit var restApi: RestApi

    init {
        DaggerMainComponent.builder().networkModule(NetworkModule(MyApplication.applicationContext())).build()
    }
}