package tn.swisaif.di

import org.koin.dsl.module
import tn.swisaif.data.repository.DefaultGalleryRepository

val repoModule = module {
    single {
        DefaultGalleryRepository(get())
    }
}