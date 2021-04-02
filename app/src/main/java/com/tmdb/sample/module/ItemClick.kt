package com.tmdb.sample.module

import com.tmdb.sample.data.PopularItem

interface ItemClick {
    fun onItemClicked(popularItem: PopularItem)
}