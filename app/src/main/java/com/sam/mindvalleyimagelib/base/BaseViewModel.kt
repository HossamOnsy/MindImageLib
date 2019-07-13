package com.sam.mindvalleyimagelib.base

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sam.mindvalleyimagelib.MyApplication.Companion.context
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {


    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    internal var subscription: Disposable
    lateinit var progressDialoge : ProgressBar

    init {
        subscription = CompositeDisposable()
        progressDialoge = ProgressBar(context)

    }

    fun onRetrieveDataError(error: Throwable?) {

        loadingVisibility.value = View.GONE
        if (error != null && error.message != null)
            errorMessage.value = error.message
        else
            errorMessage.value = "Error Occured , Please try again later ..."
    }


    fun onRetrieveDataFinish() {

        loadingVisibility.value = View.GONE
        progressDialoge.visibility = View.GONE
    }

    fun onRetrieveDataStart() {
        errorMessage.value = null
        loadingVisibility.value = View.VISIBLE
        progressDialoge.visibility = View.VISIBLE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}