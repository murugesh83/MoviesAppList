package com.tmdb.sample.module

import android.util.Log
import com.tmdb.sample.data.MovieDetails
import com.tmdb.sample.data.PopularItem
import com.tmdb.sample.data.PopularService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularRepository @Inject constructor(
    private val popularService: PopularService
) {
    private val s = PopularRepository::class.java.simpleName
    suspend fun getPopularList(pageNumber: Int): List<PopularItem> {
        Log.d(s, "getPopularList  : ")
        return withContext(Dispatchers.IO) {
            popularService.popularList(pageNumber).popularItem
        }
    }

    suspend fun getMovieDetails(movieId: String): MovieDetails {
        Log.d(s, "getMovieDetails  : $movieId")
        return withContext(Dispatchers.IO) {
            popularService.getMovieDetails(movieId)
        }
    }
}