package com.tmdb.sample.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.tmdb.sample.data.MovieDetails
import com.tmdb.sample.module.PopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val popularRepository: PopularRepository
) : ViewModel() {
    private val s = DetailsViewModel::class.java.simpleName
    private val mLivedata = MutableLiveData<String>()

    val detailsLiveData: LiveData<MovieDetails> =
        mLivedata.switchMap { movieId ->
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
                Log.d(s, "mLivedata")
                if (movieId.isNotEmpty()) {
                    emit(
                        popularRepository.getMovieDetails(movieId)
                    )
                }
            }
        }


    fun loadMovieDetails(movieId: String) {
        Log.d(s, "loadMovieDetails")
        viewModelScope.launch { mLivedata.value = movieId }
    }
}