package tn.swisaif.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tn.swisaif.data.models.Albums
import tn.swisaif.data.models.Photos
import tn.swisaif.data.models.Users

interface GalleryAPI {
    @GET("users")
    suspend fun getUsers(
    ): Response<Users>

    @GET("albums")
    suspend fun getAlbums(
    ): Response<Albums>

    @GET("photos")
    suspend fun getPhotos(
        @Query("albumId") albumId: Int,
    ): Response<Photos>
}