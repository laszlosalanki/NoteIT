package hu.unideb.noteit.edit_note

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.unideb.noteit.database.NotesDatabaseDao
import hu.unideb.noteit.notes.NotesViewModel

class EditNoteViewModelFactory(
    private val dataSource: NotesDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditNoteViewModel::class.java)) {
            return EditNoteViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}