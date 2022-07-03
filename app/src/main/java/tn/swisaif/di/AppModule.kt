package tn.swisaif.di

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.swisaif.BuildConfig
import tn.swisaif.data.api.GalleryAPI
import tn.swisaif.data.repository.DefaultGalleryRepository
import tn.swisaif.data.repository.GalleryRepository
import tn.swisaif.utils.Constants
import tn.swisaif.utils.NetworkHelper


val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), Constants.BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<GalleryRepository> {
        return@single DefaultGalleryRepository(get())
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): GalleryAPI =
    retrofit.create(GalleryAPI::class.java)