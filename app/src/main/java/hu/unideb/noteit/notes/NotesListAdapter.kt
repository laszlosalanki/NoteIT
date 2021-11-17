package hu.unideb.noteit.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.noteit.R
import hu.unideb.noteit.database.Note
import hu.unideb.noteit.databinding.NoteListItemBinding
import java.text.DateFormat
import java.text.SimpleDateFormat

//First, manual attempt for the adapter

/*class NotesListAdapter(private val context: Context, private val dataSet: ArrayList<Note>) :
    RecyclerView.Adapter<NotesListAdapter.NotesListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.note_list_item, parent, false)
        return NotesListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {
        holder.noteName?.text = dataSet[position].title
        //holder.dateOfCreation?.text = dataSet[position].creationTime.time.toString()
        val formattedDateTime: String = DateFormat.getDateTimeInstance().format(dataSet[position].creationTime)
        holder.dateOfCreation?.text = formattedDateTime
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
}*/

class NotesListAdapter : ListAdapter<Note, NotesListAdapter.ViewHolder>(NotesDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: NoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Note) {
            binding.note = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class NotesDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}