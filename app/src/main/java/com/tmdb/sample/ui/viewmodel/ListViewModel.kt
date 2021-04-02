package com.tmdb.sample.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.tmdb.sample.data.PopularItem
import com.tmdb.sample.module.PopularRepository
import com.tmdb.sample.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val popularRepository: PopularRepository
) :
    ViewModel() {
    private val s = ListViewModel::class.java.simpleName
    private var pageNumber = 1
    val mLiveData = MutableLiveData<List<PopularItem>>()

    suspend fun getPopularData() {
        Log.d(s, "page : $pageNumber")
        if (Utils.isConnected(context))
            mLiveData.value = popularRepository.getPopularList(pageNumber++)
    }
}