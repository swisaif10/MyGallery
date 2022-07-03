package tn.swisaif.data.repository

import tn.swisaif.data.api.GalleryAPI
import tn.swisaif.data.models.Albums
import tn.swisaif.data.models.Photos
import tn.swisaif.data.models.Users
import tn.swisaif.utils.Resource

class DefaultGalleryRepository(private val galleryApi: GalleryAPI) : GalleryRepository {
    override suspend fun getUsers(): Resource<Users> {
        return try {
            val response = galleryApi.getUsers()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun getAlbums(): Resource<Albums> {
        return try {
            val response = galleryApi.getAlbums()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun getPhotosPerAlbum(albumId: Int): Resource<Photos> {
        return try {
            val response = galleryApi.getPhotos(albumId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }

    }
}