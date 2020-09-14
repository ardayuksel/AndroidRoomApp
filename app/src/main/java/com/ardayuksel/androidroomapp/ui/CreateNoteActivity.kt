package com.ardayuksel.androidroomapp.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ardayuksel.androidroomapp.R
import com.ardayuksel.androidroomapp.data.db.Note
import com.ardayuksel.androidroomapp.data.viewmodels.NotesViewModel
import com.ardayuksel.androidroomapp.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_create_note.*

class CreateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportActionBar?.hide()

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()
            val note = Note(title, content)

            var notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

            if (title.isEmpty() || content.isEmpty()) {
                ToastUtil.makeToast(this, "Please enter all of values")
            } else {
                notesViewModel.insertNotes(note)
                startActivity(Intent(this, ListActivity::class.java))
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ListActivity::class.java))
    }
}