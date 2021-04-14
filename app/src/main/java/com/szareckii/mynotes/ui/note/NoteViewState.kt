package com.szareckii.mynotes.ui.note

import com.szareckii.mynotes.data.entity.Note
import com.szareckii.mynotes.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {
    class Data(val note: Note? = null, val isDeleted: Boolean = false)
}