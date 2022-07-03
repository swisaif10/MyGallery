package tn.swisaif.ui.view_models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tn.swisaif.MainCoroutineRule
import tn.swisaif.data.repository.FakeGalleryRepository
import tn.swisaif.data.repository.GalleryRepository
import tn.swisaif.getOrAwaitValueTest
import tn.swisaif.utils.Status

@ExperimentalCoroutinesApi
class AlbumsViewModelTest {
    private lateinit var repository: GalleryRepository
    private lateinit var viewModel: AlbumsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repository = FakeGalleryRepository()
        viewModel = AlbumsViewModel(repository)
    }

    @Test
    fun `check if we receive the authors data`() {
        val value = viewModel.users.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `check if we receive the album data`() {
        val value = viewModel.albums.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }
}