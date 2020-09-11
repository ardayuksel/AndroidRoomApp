package com.ardayuksel.androidroomapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.ardayuksel.androidroomapp.R
import com.ardayuksel.androidroomapp.adapters.NoteAdapter
import com.ardayuksel.androidroomapp.data.Note
import com.ardayuksel.androidroomapp.data.NotesViewModel
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), NoteAdapter.OnItemClickListener {
    lateinit var noteAdapter: NoteAdapter
    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        supportActionBar?.hide()

        recyclerview.apply {
            recyclerview.layoutManager = LinearLayoutManager(this@ListActivity)
            noteAdapter = NoteAdapter(this@ListActivity)
            adapter = noteAdapter
            val divider = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(divider)
        }

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        notesViewModel.getAllNotesObservers().observe(this, Observer {
            noteAdapter.setListData(ArrayList(it))
            noteAdapter.notifyDataSetChanged()
        })

        btnAddList.setOnClickListener {
            startActivity(Intent(this, CreateNoteActivity::class.java))
        }
    }

    override fun onItemClick(position: Int) {
        var newPosition = position + 1
        Toast.makeText(this, "Pressed $newPosition. item", Toast.LENGTH_SHORT).show()
    }

    override fun onTitleClickListener(note: Note) {
        notesViewModel.deleteNote(note)
    }
}