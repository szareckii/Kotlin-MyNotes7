package com.szareckii.mynotes.data.provider

import androidx.lifecycle.LiveData
import com.szareckii.mynotes.data.entity.Note
import com.szareckii.mynotes.data.entity.User
import com.szareckii.mynotes.data.model.NoteResult

interface DataProvider {
    fun getNotes(): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun getCurrentUser() :LiveData<User?>
    fun deleteNote(id: String): LiveData<NoteResult>
}