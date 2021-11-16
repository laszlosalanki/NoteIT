package hu.unideb.noteit.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.noteit.R
import hu.unideb.noteit.database.Note

class NotesListAdapter(private val context: Context, private val dataSet: ArrayList<Note>) :
    RecyclerView.Adapter<NotesListAdapter.NotesListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.note_list_item, parent, false)
        return NotesListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {
        holder.noteName?.text = dataSet[position].noteName
        holder.dateOfCreation?.text = dataSet[position].creationDate.time.toString()
        holder.category?.text = dataSet[position].category
    }

    override fun getItemCount(): Int {
        return dataSet.count()
    }

    class NotesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteName: TextView? = itemView.findViewById(R.id.viewNoteTitle)
        var dateOfCreation: TextView? = itemView.findViewById(R.id.viewCreationDate)
        var category: TextView? = itemView.findViewById(R.id.viewCategory)
    }
}