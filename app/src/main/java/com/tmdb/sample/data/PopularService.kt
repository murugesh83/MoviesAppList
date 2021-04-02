package com.tmdb.sample.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularService {

    @GET("popular?api_key=a371f045b3ab77663a5c1143a9fb9da1")
    suspend fun popularList(@Query("page") pageNumber: Int): Popular

    @GET("{movieId}?api_key=a371f045b3ab77663a5c1143a9fb9da1")
    suspend fun getMovieDetails(@Path("movieId") id: String): MovieDetails
}