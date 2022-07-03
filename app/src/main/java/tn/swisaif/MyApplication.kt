package tn.swisaif

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tn.swisaif.di.appModule
import tn.swisaif.di.repoModule
import tn.swisaif.di.viewModelModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}