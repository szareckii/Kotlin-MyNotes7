package com.szareckii.mynotes.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.szareckii.mynotes.data.NotesRepository
import com.szareckii.mynotes.data.entity.User
import com.szareckii.mynotes.data.errors.NoAuthException
import com.szareckii.mynotes.data.model.NoteResult
import com.szareckii.mynotes.ui.main.MainViewModel
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository = mockk<NotesRepository>()
    private val currentUserLiveData  = MutableLiveData<User?>()
    private val testUser = User("Neo", "neo@gmail.com")

    private lateinit var viewModel: SplashViewModel

    @Before
    fun setup(){
        clearAllMocks()

        every {mockRepository.getCurrentUser()} returns currentUserLiveData
        viewModel = SplashViewModel(mockRepository)
    }

    @Test
    fun `should getCurrentUser once`() {
        viewModel.requestUser()
        verify(exactly = 1) { mockRepository.getCurrentUser() }
    }

    @Test
    fun `should return SplashViewState auth true`() {
        var result: Boolean? = null
        viewModel.viewStateLiveData.observeForever{
            result = it.data
        }
        viewModel.requestUser()
        currentUserLiveData.value = testUser
        assertEquals(result, true)
    }

    @Test
    fun `should return SplashViewState auth error`() {
        var result: Boolean? = null
        var testError: Boolean? = true

        viewModel.viewStateLiveData.observeForever{
            result = it.error is NoAuthException
        }
        viewModel.requestUser()
        currentUserLiveData.value = null
        Assert.assertEquals(testError, result)
    }

    @Test
    fun `should remove observer`() {
        viewModel.onCleared()
        assertFalse(currentUserLiveData.hasObservers())
    }
}