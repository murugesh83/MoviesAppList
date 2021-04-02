package com.tmdb.sample.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Popular(
    @SerializedName("page") @Expose var page: String,
    @SerializedName("results") @Expose var popularItem: List<PopularItem>,
    @SerializedName("total_pages") @Expose var totalPages: String,
    @SerializedName("total_results") @Expose var totalResults: String
)