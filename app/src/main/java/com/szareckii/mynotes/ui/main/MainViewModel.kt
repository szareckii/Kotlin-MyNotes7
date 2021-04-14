package com.szareckii.mynotes.ui.main

import androidx.annotation.VisibleForTesting
import com.szareckii.mynotes.data.NotesRepository
import com.szareckii.mynotes.data.entity.Note
import com.szareckii.mynotes.data.model.NoteResult
import com.szareckii.mynotes.ui.base.BaseViewModel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class MainViewModel(val notesRepository: NotesRepository) : BaseViewModel<List<Note>?>() {

    private val repositoryNotes = notesRepository.getNotes()

    init {
        launch {
            repositoryNotes.consumeEach {result ->
                when(result) {
                    is NoteResult.Success<*> -> setData(result.data as? List<Note>)
                    is NoteResult.Error -> setError(result.error)
                }
            }
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        repositoryNotes.cancel()
    }
}