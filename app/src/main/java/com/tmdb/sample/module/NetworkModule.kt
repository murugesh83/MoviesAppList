package com.tmdb.sample.module

import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import com.tmdb.sample.data.PopularService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun getPopularService(): PopularService {
        var gson = GsonBuilder().setLenient().create()
        var retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(PopularService::class.java)
    }


    @Provides
    fun getPicasso(): Picasso {
        return Picasso.get()
    }

}