package com.szareckii.mynotes.data

import com.szareckii.mynotes.data.entity.Note
import com.szareckii.mynotes.data.provider.DataProvider

class NotesRepository(val dataProvider: DataProvider) {

    fun getNotes() = dataProvider.subscribeToNotes()
    suspend fun saveNote(note: Note) = dataProvider.saveNote(note)
    suspend fun getNoteById(id: String) = dataProvider.getNoteById(id)
    suspend fun getCurrentUser() = dataProvider.getCurrentUser()
    suspend fun deleteNote(id: String) = dataProvider.deleteNote(id)
}