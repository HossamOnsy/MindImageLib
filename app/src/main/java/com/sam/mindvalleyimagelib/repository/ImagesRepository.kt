package com.sam.mindvalleyimagelib.repository

import com.sam.mindvalleyimagelib.models.ImagesResponseModel
import com.sam.mindvalleyimagelib.network.RestApi
import io.reactivex.Observable

class ImagesRepository(var restApi: RestApi) {

    // We will assume this is from the Datastore
    var cachedImages = ArrayList<ImagesResponseModel>()


    fun getCachedImages(): Observable<List<ImagesResponseModel>> {

        return Observable.fromArray(cachedImages)
    }

    //
    fun getImages(size: Int): Observable<List<ImagesResponseModel>> {


        if (size == 0 || size >= cachedImages.size) {

            // TODO here there will be a limit to the amount of elements that are fetched from the database
            // which would be specified in the response total pages, current page ... etc however this is not the case here
            // and hence the cache will be only 100 elements however the data will be able to reload as if it has no end
            return restApi.getImages().doOnNext {

                if (cachedImages.size > 100) {
                    it.forEach {
                        cachedImages.removeAt(0)
                        cachedImages.add(cachedImages.size - 1, it)
                    }

                }else cachedImages.addAll(it)


            }
                .onErrorReturn { null }
        } else {
            return getCachedImages().doOnNext {
                it.subList(size,size+(it.size)%9)
            }
        }
    }


}
