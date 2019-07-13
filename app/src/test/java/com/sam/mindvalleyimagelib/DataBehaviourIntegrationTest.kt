package com.sam.mindvalleyimagelib

import com.sam.mindvalleyimagelib.network.RestApi
import com.sam.mindvalleyimagelib.repository.ImagesRepository
import com.sam.mindvalleyimagelib.ui.viewmodels.ImagesVM
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class DataBehaviourIntegrationTest {

    @Mock
    var restApi: RestApi? = null

    @Mock
    var repo: ImagesRepository? = null

    @Mock
    var viewModel: ImagesVM? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun verifyIntegration() {
        viewModel = ImagesVM()
        viewModel!!.repository = repo!!

        viewModel?.getImages(0)
        verify(repo, times(1))?.getImages(0)

    }




}