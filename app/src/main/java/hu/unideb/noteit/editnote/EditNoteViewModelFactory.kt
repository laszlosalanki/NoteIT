package hu.unideb.noteit.editnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.unideb.noteit.database.NotesDatabaseDao

class EditNoteViewModelFactory(
    private val noteKey: Long,
    private val dataSource: NotesDatabaseDao
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditNoteViewModel::class.java)) {
            return EditNoteViewModel(noteKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}