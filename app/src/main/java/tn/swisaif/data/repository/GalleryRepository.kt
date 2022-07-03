package tn.swisaif.data.repository

import tn.swisaif.data.models.Albums
import tn.swisaif.data.models.Photos
import tn.swisaif.data.models.Users
import tn.swisaif.utils.Resource

interface GalleryRepository {
    suspend fun getUsers(): Resource<Users>
    suspend fun getAlbums(): Resource<Albums>
    suspend fun getPhotosPerAlbum(albumId: Int): Resource<Photos>
}