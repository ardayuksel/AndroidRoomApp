package com.ardayuksel.androidroomapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): List<Note>

    @Insert
    fun insertAll(vararg note: Note)

    @Delete
    fun delete(vararg note: Note)
}