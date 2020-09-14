package com.ardayuksel.androidroomapp.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ardayuksel.androidroomapp.data.db.AppDatabase
import com.ardayuksel.androidroomapp.data.db.Note

class NotesViewModel(app: Application) : AndroidViewModel(app) {
    var allNotes: MutableLiveData<List<Note>>

    init {
        allNotes = MutableLiveData()
        getAllNotes()
    }

    fun getAllNotesObservers(): MutableLiveData<List<Note>> {
        return allNotes
    }

    fun getAllNotes() {
        var noteDao = AppDatabase.getAppDatabase((getApplication()))?.noteDao()
        var list = noteDao?.getAllNotes()
        allNotes.postValue(list)
    }

    fun insertNotes(note: Note) {
        var noteDao = AppDatabase.getAppDatabase(getApplication())?.noteDao()
        noteDao?.insertAll(note)
        getAllNotes()
    }

    fun deleteNote(note: Note) {
        var noteDao = AppDatabase.getAppDatabase(getApplication())?.noteDao()
        noteDao?.delete(note)
        getAllNotes()
    }
}