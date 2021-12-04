package hu.unideb.noteit.editnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.unideb.noteit.database.NotesDatabaseDao
import hu.unideb.noteit.databinding.FragmentEditNoteBinding

class EditNoteViewModelFactory(
    private val noteKey: Long,
    private val dataSource: NotesDatabaseDao,
    private val binding: FragmentEditNoteBinding
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditNoteViewModel::class.java)) {
            return EditNoteViewModel(noteKey, dataSource, binding) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}