package com.sam.mindvalleyimagelib.network

import com.sam.mindvalleyimagelib.models.ImagesResponseModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface RestApi {

    @GET("raw/wgkJgazE")
    fun getImages(
    ): Observable<List<ImagesResponseModel>>

}