package hu.unideb.noteit.notes

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import com.todkars.shimmer.ShimmerRecyclerView
import hu.unideb.noteit.R
import hu.unideb.noteit.database.Note
import hu.unideb.noteit.database.NotesDatabase
import hu.unideb.noteit.databinding.FragmentNotesBinding
import java.util.*
import kotlin.collections.ArrayList

class NotesFragment : Fragment() {

    private var recyclerView: ShimmerRecyclerView? = null
    private var speedDialView: SpeedDialView? = null
    private var notesList = ArrayList<Note>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Show the previously hidden actionbar
        (activity as AppCompatActivity).title = getString(R.string.my_notes)
        (activity as AppCompatActivity).supportActionBar?.show()

        val binding: FragmentNotesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_notes, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = NotesDatabase.getInstance(application).notesDatabaseDao
        val viewModelFactory = NotesViewModelFactory(dataSource, application)

        val notesViewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)


        val speedDialActionItemAddNote = SpeedDialActionItem.Builder(R.id.addNote, R.drawable.ic_baseline_add_24)
            .setLabel(R.string.add_note)
            .setFabImageTintColor(Color.WHITE)
            .setLabelClickable(true)
            .create()

        val speedDialActionItemClearNotes = SpeedDialActionItem.Builder(R.id.clearNotes, R.drawable.ic_baseline_clear_24)
            .setLabel(R.string.clear_notes)
            .setFabImageTintColor(Color.WHITE)
            .setLabelClickable(true)
            .create()

        val speedDialActionItemTest = SpeedDialActionItem.Builder(R.id.test, R.drawable.ic_baseline_architecture_24)
            .setLabel(R.string.test)
            .setFabImageTintColor(Color.WHITE)
            .setLabelClickable(true)
            .create()

        binding.speedDial.addActionItem(speedDialActionItemAddNote)
        binding.speedDial.addActionItem(speedDialActionItemClearNotes)
        binding.speedDial.addActionItem(speedDialActionItemTest)

        binding.speedDial.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when(actionItem.id){
                R.id.addNote -> {
                    notesViewModel.onSaveNote()
                    return@OnActionSelectedListener false
                }
                R.id.clearNotes -> {
                    notesViewModel.onClear()
                    return@OnActionSelectedListener false
                }
                R.id.test -> {
                    val navOptions = NavOptions.Builder().setPopUpTo(R.id.editNoteFragment, true).build()
                    findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(), navOptions)
                }
            }
            false
        })

        binding.notesViewModel = notesViewModel

        val adapter = NotesListAdapter()
        binding.notesList.adapter = adapter

        notesViewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner( this )

        return binding.root
    }

}