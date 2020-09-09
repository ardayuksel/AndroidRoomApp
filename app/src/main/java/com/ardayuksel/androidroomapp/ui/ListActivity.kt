package com.ardayuksel.androidroomapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ardayuksel.androidroomapp.R
import com.ardayuksel.androidroomapp.adapters.NoteAdapter
import com.ardayuksel.androidroomapp.db.AppDatabase
import com.ardayuksel.androidroomapp.db.Note
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), NoteAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        supportActionBar?.hide()
        val notes: List<Note>
        val db: AppDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "notes")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        notes = db.noteDao().getAllNotes()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = NoteAdapter(notes, this, this)

        btnAddList.setOnClickListener {
            startActivity(Intent(this, CreateNoteActivity::class.java))
            finish()
        }
    }

    override fun onItemClick(position: Int) {
        var newPosition = position + 1
        Toast.makeText(this, "Pressed $newPosition. item.", Toast.LENGTH_SHORT).show()
    }
}