package tn.swisaif.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tn.swisaif.ui.view_models.AlbumsViewModel
import tn.swisaif.ui.view_models.PhotosViewModel

val viewModelModule = module {
    viewModel() {
        AlbumsViewModel(get())
    }
    viewModel() {
        PhotosViewModel(get())
    }
}
