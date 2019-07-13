package com.sam.mindvalleyimagelib.di.modules

import android.content.Context
import com.sam.mindvalleyimagelib.MyApplication
import com.sam.mindvalleyimagelib.network.RestApi
import com.sam.mindvalleyimagelib.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.sam.mindvalleyimagelib.MyApplication.Companion.applicationContext
import com.sam.mindvalleyimagelib.utils.AppUtils.isNetworkAvailable


@Module
class NetworkModule(val app: MyApplication) {


    @Provides
    @Singleton
    fun app(): MyApplication {
        return app
    }

    @Provides
    @Singleton
    fun context(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        client.addInterceptor(interceptor)
            .addInterceptor {
                val context = applicationContext()
                val originalResponse = it.proceed(it.request())
                if (isNetworkAvailable(context)) {
                    val maxAge = 60 // read from cache for 1 minute
                     originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60   // tolerate 1-hour stale
                     originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }
            }

        return client.build()
    }


    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Singleton
    internal fun provideRetrofitInterface(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

}