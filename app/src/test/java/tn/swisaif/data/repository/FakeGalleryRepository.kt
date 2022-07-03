package tn.swisaif.data.repository

import androidx.lifecycle.MutableLiveData
import tn.swisaif.data.models.*
import tn.swisaif.utils.Resource
import tn.swisaif.utils.Status

class FakeGalleryRepository() : GalleryRepository {
    private val usersItems = mutableListOf<UsersItem>()
    private val observableUsersItems = MutableLiveData<List<UsersItem>>(usersItems)

    private val albumItems = mutableListOf<AlbumsItem>()
    private val observableAlbumItemss = MutableLiveData<List<AlbumsItem>>(albumItems)

    private val photosItems = mutableListOf<PhotosItem>()
    private val observablePhotosItems = MutableLiveData<List<PhotosItem>>(photosItems)
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableUsersItems.postValue(usersItems)
        observableAlbumItemss.postValue(albumItems)
        observablePhotosItems.postValue(photosItems)
    }

    override suspend fun getUsers(): Resource<Users> {
        var users = Users()
        usersItems.addAll(
            listOf(
                UsersItem(
                    Address("adresse X", Geo("", ""), "", "", ""),
                    Company("", "", "company X"),
                    "mail@XX.com",
                    1,
                    "auteur 1",
                    "phone 1",
                    "usernameAuteur1",
                    "url"
                ),
                UsersItem(
                    Address("adresse Y", Geo("", ""), "", "", ""),
                    Company("", "", "company Y"),
                    "mail2@XX.com",
                    2,
                    "auteur 2",
                    "phone 2",
                    "usernameAuteur2",
                    "url"
                ),
                UsersItem(
                    Address("adresse W", Geo("", ""), "", "", ""),
                    Company("", "", "company W"),
                    "mail@XWX.com",
                    3,
                    "auteur 3",
                    "phone 3",
                    "usernameAuteur3",
                    "url"
                )
            )
        )
        users.addAll(usersItems)
        refreshLiveData()
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource(Status.SUCCESS, users, "")
        }
    }


    override suspend fun getAlbums(): Resource<Albums> {
        var album = Albums()
        albumItems.addAll(
            listOf(
                AlbumsItem(1, "album1", 1),
                AlbumsItem(2, "album2", 1),
                AlbumsItem(3, "album3", 2),

                )
        )
        album.addAll(albumItems)
        refreshLiveData()

        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource(Status.SUCCESS, album, "")
        }

    }


    override suspend fun getPhotosPerAlbum(albumId: Int): Resource<Photos> {
        var photos = Photos()
        photosItems.addAll(
            listOf(
                PhotosItem(1, 1, "", "photo1", ""),
                PhotosItem(1, 2, "", "photo2", ""),
                PhotosItem(2, 3, "", "photo3", ""),
                PhotosItem(2, 4, "", "photo4", ""),
                PhotosItem(3, 5, "", "photo5", ""),
                PhotosItem(3, 6, "", "photo6", ""),
                PhotosItem(3, 7, "", "photo7", ""),
            )
        )
        photos.addAll(photosItems)
        refreshLiveData()
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource(Status.SUCCESS, photos, "")
        }
    }


}