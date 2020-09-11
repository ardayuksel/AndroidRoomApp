package com.ardayuksel.androidroomapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ardayuksel.androidroomapp.R
import com.ardayuksel.androidroomapp.data.Note
import com.ardayuksel.androidroomapp.data.NotesViewModel
import kotlinx.android.synthetic.main.activity_create_note.*

class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        supportActionBar?.hide()

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            val note = Note(title, content)

            var notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
            notesViewModel.insertNotes(note)

            startActivity(Intent(this, ListActivity::class.java))
        }
    }
}