package hu.unideb.noteit.edit_note

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import hu.unideb.noteit.R
import hu.unideb.noteit.database.Note
import hu.unideb.noteit.database.NotesDatabase
import hu.unideb.noteit.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    private var note = Note()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).title = getString(R.string.edit_note)

        val binding: FragmentEditNoteBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_edit_note, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = NotesDatabase.getInstance(application).notesDatabaseDao
        val viewModelFactory = EditNoteViewModelFactory(dataSource, application)

        val editNoteViewModel = ViewModelProvider(this, viewModelFactory).get(EditNoteViewModel::class.java)

        ArrayAdapter.createFromResource(
            requireNotNull(activity).applicationContext,
            R.array.categories_array,
            R.layout.spinner_item
            //android.R.layout.simple_spinner_item
        ).also { adapter ->
            //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            binding.spinnerCategories.adapter = adapter
        }

        binding.buttonClear.setOnClickListener {
            binding.editTextNote.text.clear()
        }

        binding.editNoteViewModel = editNoteViewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }
}