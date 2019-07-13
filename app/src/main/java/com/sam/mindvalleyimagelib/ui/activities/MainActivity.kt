package com.sam.mindvalleyimagelib.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sam.mindvalleyimagelib.R
import com.sam.mindvalleyimagelib.models.ImagesResponseModel
import com.sam.mindvalleyimagelib.ui.adapters.UsersAdapter
import com.sam.mindvalleyimagelib.ui.viewmodels.ImagesVM
import kotlinx.android.synthetic.main.recycler_layout.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var viewmodel: ImagesVM
    var currentSize = 0
    lateinit var toast: Toast
    lateinit var adapter: UsersAdapter

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_layout)

        viewmodel = ViewModelProviders.of(this).get(ImagesVM::class.java)
        toast = Toast.makeText(this, "Error Occured , please try again later ...", Toast.LENGTH_SHORT)
        recyclerInitation()
        setObservers()

        viewmodel.getImages(currentSize)

    }

    private fun recyclerInitation() {
        recycler_view.apply {
            setHasFixedSize(true)
            val arrayList = ArrayList<ImagesResponseModel>()
            layoutManager = LinearLayoutManager(this@MainActivity)
            setUpRecyclerAdapter(arrayList)
        }

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)


                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 1) {
                    viewmodel.getImages(totalItemCount)

                }
            }
        })
    }

    fun setUpRecyclerAdapter(requests: ArrayList<ImagesResponseModel>) {
        recycler_view.adapter = null
        adapter = UsersAdapter(
            requests
        )
        recycler_view.adapter = adapter
    }

    private fun setObservers() {
        successObserver()
        loadingObserver()


    }


    private fun loadingObserver() {
        viewmodel.loadingVisibility.observe(this, Observer {
            progress_bar.visibility = it

        })

    }

    private fun successObserver() {
        viewmodel.successObject.observe(this, Observer {

            adapter.updateList(it)

            Timber.d("Show me Size of Elements -> ${it.size}")
            it.forEach {
                Timber.d("Show me Data of Element -> ${it.id}")
            }
        })

    }

}
