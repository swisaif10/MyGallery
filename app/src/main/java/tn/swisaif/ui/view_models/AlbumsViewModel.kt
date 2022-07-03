package tn.swisaif.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tn.swisaif.data.models.Albums
import tn.swisaif.data.models.Users
import tn.swisaif.data.repository.GalleryRepository
import tn.swisaif.utils.Resource

class AlbumsViewModel(
    private val galleryRepository: GalleryRepository,
) : ViewModel() {
    private val _users = MutableLiveData<Resource<Users>>()
    private val _albums = MutableLiveData<Resource<Albums>>()
    val users: LiveData<Resource<Users>>
        get() = _users
    val albums: LiveData<Resource<Albums>>
        get() = _albums

    init {
        fetchUsers()
        fetchAlbums()
    }

    fun fetchUsers() {
        _users.value = Resource.loading(null)
        viewModelScope.launch {
            _users.value = galleryRepository.getUsers()
        }
    }

    fun fetchAlbums() {
        _albums.value = Resource.loading(null)
        viewModelScope.launch {
            _albums.value = galleryRepository.getAlbums()
        }
    }

}