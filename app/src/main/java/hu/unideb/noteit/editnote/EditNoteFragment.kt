package hu.unideb.noteit.editnote

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import hu.unideb.noteit.R
import hu.unideb.noteit.database.NotesDatabase
import hu.unideb.noteit.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).title = getString(R.string.edit_note)

        val binding: FragmentEditNoteBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_edit_note, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = EditNoteFragmentArgs.fromBundle(arguments)

        val dataSource = NotesDatabase.getInstance(application).notesDatabaseDao
        val viewModelFactory = EditNoteViewModelFactory(arguments.noteKey, dataSource, binding)

        val editNoteViewModel = ViewModelProvider(this, viewModelFactory).get(EditNoteViewModel::class.java)

        binding.buttonClear.setOnClickListener {
            binding.editTextNote.text.clear()
        }

        binding.editNoteViewModel = editNoteViewModel
        binding.setLifecycleOwner(this)

        editNoteViewModel.navigateToNotesFragment.observe(this, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    EditNoteFragmentDirections.actionEditNoteFragmentToNotesFragment()
                )
                editNoteViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}