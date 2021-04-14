package com.szareckii.mynotes.ui.splash

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.szareckii.mynotes.data.NotesRepository
import com.szareckii.mynotes.data.entity.User
import com.szareckii.mynotes.data.errors.NoAuthException
import com.szareckii.mynotes.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel(val notesRepository: NotesRepository): BaseViewModel<Boolean>() {

    var currentUserLiveData: LiveData<User?>? = null

    fun requestUser() = launch{
        notesRepository.getCurrentUser()?.let {
            setData(true)
        } ?: setError(NoAuthException())
    }
}