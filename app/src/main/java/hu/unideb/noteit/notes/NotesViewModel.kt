package hu.unideb.noteit.notes

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.unideb.noteit.database.Note
import hu.unideb.noteit.database.NotesDatabaseDao
import kotlinx.coroutines.launch

class NotesViewModel(
    dataSource: NotesDatabaseDao,
    application: Application
) : ViewModel() {
    val dataBase = dataSource

    val notes = dataBase.getAllNotesIdOrdered()

    fun onSaveNote() {
        viewModelScope.launch {
            val newNote = Note()
            insert(newNote)
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

//    init {
//        initializeNotes()
//    }
//
//    private fun initializeNotes() {
//
//    }

    private suspend fun insert(note: Note) {
        dataBase.insert(note)
    }

    private suspend fun update(note: Note) {
        dataBase.update(note)
    }

    suspend fun clear() {
        dataBase.clear()
    }
}