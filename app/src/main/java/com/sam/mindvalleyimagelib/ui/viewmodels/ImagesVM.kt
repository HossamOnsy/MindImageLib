package com.sam.mindvalleyimagelib.ui.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sam.mindvalleyimagelib.MyApplication.Companion.applicationContext
import com.sam.mindvalleyimagelib.base.BaseViewModel
import com.sam.mindvalleyimagelib.di.component.DaggerMainComponent
import com.sam.mindvalleyimagelib.di.modules.NetworkModule
import com.sam.mindvalleyimagelib.models.ImagesResponseModel
import com.sam.mindvalleyimagelib.repository.ImagesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImagesVM : BaseViewModel() {

    @Inject
    lateinit var repository: ImagesRepository

    val successObject: MutableLiveData<List<ImagesResponseModel>> = MutableLiveData()


    init {
        DaggerMainComponent.builder().networkModule(NetworkModule(applicationContext())).build().inject(this)
    }

    fun getImages(currentSize : Int) {
        subscription = repository.getImages(currentSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(
                {

                    onRetrieveOnSuccess(it)

                },
                { error -> onRetrieveDataError(error) }
            )
    }

    fun cancelRequest() {
        subscription.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrieveOnSuccess(response: List<ImagesResponseModel>) {

        loadingVisibility.value = View.GONE

        successObject.value = response
    }


}
