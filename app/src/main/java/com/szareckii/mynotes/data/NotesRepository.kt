package com.szareckii.mynotes.data

import com.szareckii.mynotes.data.entity.Note
import com.szareckii.mynotes.data.provider.DataProvider
import com.szareckii.mynotes.data.provider.FireStoreDataProvider

class NotesRepository(val dataProvider: DataProvider) {

    fun getNotes() = dataProvider.getNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
    fun getCurrentUser() = dataProvider.getCurrentUser()
    fun deleteNote(id: String) = dataProvider.deleteNote(id)
}