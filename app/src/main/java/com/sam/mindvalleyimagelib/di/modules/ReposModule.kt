package com.sam.mindvalleyimagelib.di.modules


import com.sam.mindvalleyimagelib.network.RestApi
import com.sam.mindvalleyimagelib.repository.ImagesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ReposModule {

    @Provides
    @Singleton
    internal fun provideImagesRepository(restApi: RestApi): ImagesRepository {
        return ImagesRepository(restApi)
    }

}