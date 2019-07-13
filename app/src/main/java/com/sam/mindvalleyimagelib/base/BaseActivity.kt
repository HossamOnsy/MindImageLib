package com.sam.mindvalleyimagelib.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sam.mindvalleyimagelib.base.BaseViewModel

abstract class BaseActivity : AppCompatActivity() {

    lateinit var baseViewModel: BaseViewModel
    lateinit var toast: Toast


    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        toast = Toast.makeText(this, "awaiting", Toast.LENGTH_SHORT)
        errorObserve()
    }

    private fun errorObserve() {
        baseViewModel.errorMessage.observe(this, Observer {
            toast.cancel()
            if (it != null) {
                toast = Toast.makeText(this, it, Toast.LENGTH_SHORT)
                toast.show()
            }
        })
    }

}
