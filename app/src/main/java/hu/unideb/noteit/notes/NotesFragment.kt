package hu.unideb.noteit.notes

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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


        //recyclerView = binding.shimmerRecyclerViewNotes
        //speedDialView = binding.speedDialNotes
        //setSpeedDial()
        //etRecyclerView()

        binding.notesViewModel = notesViewModel

        val adaper = NotesListAdapter()
        binding.notesList.adapter = adaper

        notesViewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adaper.submitList(it)
            }
        })

        //binding.lifecycleOwner = viewLifecycleOwner
        binding.setLifecycleOwner( this )

        return binding.root
    }

//    private fun setSpeedDial() {
//        var speedDialActionItemAdd: SpeedDialActionItem.Builder()
//    }
//
//    private fun setRecyclerView() {
//        //recyclerView?.showShimmer()
//        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
//
//        notesList.add(Note(1, "Test note", Date(), "test"))
//        notesList.add(Note(2, "Test note 2", Date(), "test"))
//
//        recyclerView?.adapter = NotesListAdapter(requireContext(), notesList)
//    }

}