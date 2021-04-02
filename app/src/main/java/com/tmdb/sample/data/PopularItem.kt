package com.tmdb.sample.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PopularItem(

    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("adult")
    @Expose
    var adult: String,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String,
    @SerializedName("genre_ids")
    @Expose
    var genreIds: Array<String>,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String,
    @SerializedName("original_title")
    @Expose
    var originalTitle: String,
    @SerializedName("overview")
    @Expose
    var overview: String,
    @SerializedName("popularity")
    @Expose
    var popularity: String,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("video")
    @Expose
    var video: String,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: String,
    @SerializedName("vote_count")
    @Expose
    var voteCount: String
)