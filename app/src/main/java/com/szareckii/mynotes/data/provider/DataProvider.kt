package com.szareckii.mynotes.data.provider

import com.szareckii.mynotes.data.entity.Note
import com.szareckii.mynotes.data.entity.User
import com.szareckii.mynotes.data.model.NoteResult
import kotlinx.coroutines.channels.ReceiveChannel

interface DataProvider {
    fun subscribeToNotes(): ReceiveChannel<NoteResult>
    suspend fun saveNote(note: Note): Note
    suspend fun getNoteById(id: String): Note
    suspend fun deleteNote(id: String)
    suspend fun getCurrentUser(): User?
}