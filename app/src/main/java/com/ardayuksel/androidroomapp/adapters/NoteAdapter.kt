package com.ardayuksel.androidroomapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardayuksel.androidroomapp.R
import com.ardayuksel.androidroomapp.data.Note
import kotlinx.android.synthetic.main.rc_note_item.view.*

class NoteAdapter(
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var notes = ArrayList<Note>()

    fun setListData(data: ArrayList<Note>) {
        this.notes = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.rc_note_item, parent, false)
        return NoteViewHolder(inflater, listener)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NoteViewHolder(view: View, val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        var title = view.item_tv_title
        var content = view.item_tv_content

        fun bind(note: Note) {
            title.text = note.title
            content.text = note.content
            title.setOnClickListener {
                listener.onTitleClickListener(note)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onTitleClickListener(note: Note)
    }

}