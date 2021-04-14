package com.szareckii.mynotes.ui.note

import androidx.annotation.VisibleForTesting
import com.szareckii.mynotes.data.NotesRepository
import com.szareckii.mynotes.data.entity.Note
import com.szareckii.mynotes.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class NoteViewModel(val notesRepository: NotesRepository) : BaseViewModel<NoteData>() {

    private var pendingNote: Note? = null

    fun save(note : Note) {
        pendingNote = note
    }

    fun loadNote(id: String) = launch {
         try {
             notesRepository.getNoteById(id).let {
                 pendingNote = it
                 setData(NoteData(note = it))
             }
        } catch (e: Throwable) {
             setError(e)
         }
    }

    @VisibleForTesting
    fun deleteNote() = launch{
        try {
            pendingNote?.let {notesRepository.deleteNote(it.id) }
            pendingNote = null
            setData(NoteData(isDeleted = true))
        } catch (e: Throwable) {
            setError(e)
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        launch {
            pendingNote?.let {
                notesRepository.saveNote(it)
            }
        }
    }
}