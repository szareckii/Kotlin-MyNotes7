package com.szareckii.mynotes.ui.main

import com.szareckii.mynotes.data.entity.Note
import com.szareckii.mynotes.ui.base.BaseViewState

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)