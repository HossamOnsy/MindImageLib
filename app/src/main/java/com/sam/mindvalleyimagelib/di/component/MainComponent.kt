package com.sam.mindvalleyimagelib.di.component

import com.sam.mindvalleyimagelib.MyApplication
import com.sam.mindvalleyimagelib.di.modules.NetworkModule
import com.sam.mindvalleyimagelib.di.modules.ReposModule
import com.sam.mindvalleyimagelib.ui.viewmodels.ImagesVM
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (ReposModule::class)])
interface MainComponent {


    fun inject(viewModel: ImagesVM)
    fun inject(app: MyApplication)


}