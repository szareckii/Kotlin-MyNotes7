package com.szareckii.mynotes.ui.note

import com.szareckii.mynotes.data.entity.Note

data class NoteData(val note: Note? = null, val isDeleted: Boolean = false)

