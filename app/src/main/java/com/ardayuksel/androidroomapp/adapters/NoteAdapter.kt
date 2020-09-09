package com.ardayuksel.androidroomapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardayuksel.androidroomapp.R
import com.ardayuksel.androidroomapp.db.Note
import kotlinx.android.synthetic.main.activity_list.view.*
import kotlinx.android.synthetic.main.rc_note_item.view.*

class NoteAdapter(
    private val notes: List<Note>,
    private val context: Context,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rc_note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.title?.text = notes[position].title
        holder.content?.text = (notes[position].content)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var title = view.item_tv_title
        var content = view.item_tv_content

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = adapterPosition
            listener.onItemClick(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}