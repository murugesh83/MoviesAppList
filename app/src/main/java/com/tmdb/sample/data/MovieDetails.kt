package com.tmdb.sample.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDetails(
    @SerializedName("adult") @Expose var adult: String,
    @SerializedName("backdrop_path") @Expose var backdropPath: String,
    @SerializedName("belongs_to_collection") @Expose var belongsToCollection: BelongsToCollection,
    @SerializedName("budget") @Expose var budget: String,
    @SerializedName("genres") @Expose var genresList: List<genres>,
    @SerializedName("homepage") @Expose var homepage: String,
    @SerializedName("id") @Expose var id: String,
    @SerializedName("imdb_id") @Expose var imdbId: String,
    @SerializedName("original_language") @Expose var originalLanguage: String,
    @SerializedName("original_title") @Expose var original_title: String,
    @SerializedName("overview") @Expose var overview: String,
    @SerializedName("popularity") @Expose var popularity: String,
    @SerializedName("poster_path") @Expose var posterPath: String,
    @SerializedName("production_companies") @Expose var productionCompaniesList: List<ProductionCompanies>,
    @SerializedName("production_countries") @Expose var productionCountriesList: List<ProductionCountries>,
    @SerializedName("release_date") @Expose var releaseDate: String,
    @SerializedName("revenue") @Expose var revenue: String,
    @SerializedName("runtime") @Expose var runtime: String,
    @SerializedName("spoken_languages") @Expose var spokenLanguagesList: List<SpokenLanguages>,
    @SerializedName("status") @Expose var status: String,
    @SerializedName("tagline") @Expose var tagline: String,
    @SerializedName("title") @Expose var title: String,
    @SerializedName("video") @Expose var video: String,
    @SerializedName("vote_average") @Expose var voteAverage: String,
    @SerializedName("vote_count") @Expose var voteCount: String
) {

    class BelongsToCollection(
        @SerializedName("id") @Expose var id: String,
        @SerializedName("name") @Expose var name: String,
        @SerializedName("poster_path") @Expose var posterPath: String,
        @SerializedName("backdrop_path") @Expose var backdropPath: String
    )

    class genres(
        @SerializedName("id") @Expose var id: String,
        @SerializedName("name") @Expose var name: String
    )

    class ProductionCompanies(
        @SerializedName("id") @Expose var id: String,
        @SerializedName("logo_path") @Expose var logoPath: String,
        @SerializedName("name") @Expose var name: String,
        @SerializedName("origin_country") @Expose var originCountry: String
    )

    class ProductionCountries(
        @SerializedName("iso_3166_1") @Expose var iso31661: String,
        @SerializedName("name") @Expose var name: String
    )

    class SpokenLanguages(
        @SerializedName("english_name") @Expose var englishName: String,
        @SerializedName("iso_639_1") @Expose var iso6391: String,
        @SerializedName("name") @Expose var name: String
    )
}