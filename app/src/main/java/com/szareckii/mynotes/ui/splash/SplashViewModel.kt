package com.szareckii.mynotes.ui.splash

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.szareckii.mynotes.data.NotesRepository
import com.szareckii.mynotes.data.entity.User
import com.szareckii.mynotes.data.errors.NoAuthException
import com.szareckii.mynotes.ui.base.BaseViewModel

class SplashViewModel(val notesRepository: NotesRepository): BaseViewModel<Boolean?, SplashViewState>() {

    var currentUserLiveData: LiveData<User?>? = null

    private val currentUserObserver = object : Observer<User?> {
        override fun onChanged(result: User?) {
            viewStateLiveData.value = result?.let { SplashViewState(true) } ?: let {
                SplashViewState(error = NoAuthException())
            }
            currentUserLiveData?.removeObserver(this)
        }
    }

    fun requestUser(){
        currentUserLiveData = notesRepository.getCurrentUser()
        currentUserLiveData?.observeForever(currentUserObserver)
    }

    @VisibleForTesting
    public override fun onCleared() {
        currentUserLiveData?.removeObserver(currentUserObserver)
    }
}