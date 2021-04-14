package com.szareckii.mynotes.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.auth.AuthUI
import com.szareckii.mynotes.R
import com.szareckii.mynotes.data.entity.Note
import com.szareckii.mynotes.ui.base.BaseActivity
import com.szareckii.mynotes.ui.note.NoteActivity
import com.szareckii.mynotes.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<List<Note>?>() {

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    override val viewModel: MainViewModel by viewModel()
    override val layoutRes = R.layout.activity_main
    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        rv_notes.layoutManager = GridLayoutManager(this, 2)

        adapter = NotesRVAdapter{
            NoteActivity.start(this, it.id)
        }

        rv_notes.adapter = adapter

        fab.setOnClickListener {
            NoteActivity.start(this)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let { adapter.notes = it }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = MenuInflater(this).inflate(R.menu.main, menu).let {true}

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.logout -> showLogoutDialog().let { true }
                else -> false
            }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_message)
                .setPositiveButton(R.string.logout_ok) {dialog, which ->
                    logout()
                }
                .setNegativeButton(R.string.logout_cancel) {dialog, which -> dialog.dismiss() }
                .show()
    }

    private fun logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    SplashActivity.start(this)
                    finish()
                }
    }
}