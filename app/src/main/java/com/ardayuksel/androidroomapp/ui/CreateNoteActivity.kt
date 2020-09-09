package com.ardayuksel.androidroomapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.ardayuksel.androidroomapp.R
import com.ardayuksel.androidroomapp.db.AppDatabase
import com.ardayuksel.androidroomapp.db.Note
import kotlinx.android.synthetic.main.activity_create_note.*
import kotlinx.android.synthetic.main.activity_list.*

class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        supportActionBar?.hide()

        val db: AppDatabase =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "notes")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        btnSave.setOnClickListener {
            val note: Note = Note(etTitle.text.toString(), etContent.text.toString())
            db.noteDao().insertAll(note)
            startActivity(Intent(this, ListActivity::class.java))
        }

    }
}