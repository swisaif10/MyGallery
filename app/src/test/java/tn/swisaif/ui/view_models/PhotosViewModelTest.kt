package tn.swisaif.ui.view_models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tn.swisaif.MainCoroutineRule
import tn.swisaif.data.repository.FakeGalleryRepository
import tn.swisaif.data.repository.GalleryRepository
import tn.swisaif.getOrAwaitValueTest
import tn.swisaif.utils.Status

class PhotosViewModelTest {
    private lateinit var repository: GalleryRepository
    private lateinit var viewModel: PhotosViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repository = FakeGalleryRepository()
        viewModel = PhotosViewModel(repository)
    }

    @Test
    fun `check if we receive the photos data`() {
        val value = viewModel.photos.getOrAwaitValueTest()
        Truth.assertThat(value.status).isEqualTo(Status.SUCCESS)
    }
}