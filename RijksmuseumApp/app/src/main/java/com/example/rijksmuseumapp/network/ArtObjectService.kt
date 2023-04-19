package com.example.rijksmuseumapp.network

import com.example.rijksmuseumapp.entity.ArtObjectDetailResponse
import com.example.rijksmuseumapp.entity.ArtObjectResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtObjectService {

    @GET("en/collection")
    fun getArtObjects(
        @Query("key") apiKey: String?,
        @Query("format") format: String?,
        @Query("ps") resultsPerPage: Int,
        @Query("imgonly") imgOnly: Boolean,
        @Query("s") sortBy: String?,
        @Query("p") currentPage: Int
    ): Call<ArtObjectResponse?>?

    @GET("en/collection/{objectNumber}")
    fun getArtObjectDetails(
        @Path("objectNumber") objectNumber: String?,
        @Query("key") apiKey: String?,
        @Query("format") format: String?
    ): Call<ArtObjectDetailResponse?>?

}