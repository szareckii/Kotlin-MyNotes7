package com.szareckii.mynotes.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.szareckii.mynotes.data.NotesRepository
import com.szareckii.mynotes.data.provider.DataProvider
import com.szareckii.mynotes.data.provider.FireStoreDataProvider
import com.szareckii.mynotes.ui.main.MainViewModel
import com.szareckii.mynotes.ui.note.NoteViewModel
import com.szareckii.mynotes.ui.splash.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single<DataProvider> { FireStoreDataProvider(get(), get()) }
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}
