package com.sam.mindvalleyimagelib.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sam.mindvalleyimagelib.MyApplication.Companion.context


// Made is as a Util Function so later on it would used whenever an Exchanging Rate is required
// Of course we can change currencyRateMapping -> fetch it from Room or the last saved CurrencyConversionList

object AppUtils {

    fun setIntoSharedPref(context: Context?, key: String, value: String) {
        val sharedPref =
            context?.getSharedPreferences(
                context.getString(com.sam.mindvalleyimagelib.R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
                ?: return
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getFromSharedPref(context: Context?, key: String): String? {
        val sharedPref =
            context?.getSharedPreferences(
                context.getString(com.sam.mindvalleyimagelib.R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
        return sharedPref?.getString(key, "")
    }


    fun setImageFromUri(uri: String, imageView: ImageView) {
        Glide.with(context).load(uri).into(imageView)
    }

    fun setImageFromDrawable(resourceDrawable: Int?, imageView: ImageView) {
        Glide.with(context).load(resourceDrawable).into(imageView)
    }


    fun isNetworkAvailable(context: Context): Boolean {
        val connectMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = connectMgr.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}