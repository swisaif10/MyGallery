package tn.swisaif.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tn.swisaif.data.models.Photos
import tn.swisaif.data.repository.GalleryRepository
import tn.swisaif.utils.Resource

class PhotosViewModel(
    private val galleryRepository: GalleryRepository,
) : ViewModel() {
    companion object {
        var albumId: Int = -1
    }

    private val _photos = MutableLiveData<Resource<Photos>>()
    val photos: LiveData<Resource<Photos>>
        get() = _photos

    init {
        fetchPhotosPerAlbum(albumId)

    }

    private fun fetchPhotosPerAlbum(albumId: Int) {
        _photos.postValue(Resource.loading(null))
        viewModelScope.launch {
            _photos.value = galleryRepository.getPhotosPerAlbum(albumId)
        }
    }

}