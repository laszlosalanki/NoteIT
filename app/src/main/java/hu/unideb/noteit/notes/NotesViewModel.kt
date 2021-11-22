package hu.unideb.noteit.notes

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.LiveData
import hu.unideb.noteit.database.Note
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.unideb.noteit.database.NotesDatabaseDao
import kotlinx.coroutines.launch

class NotesViewModel(
    dataSource: NotesDatabaseDao,
    application: Application
) : ViewModel() {
    val dataBase = dataSource

    private var actNote = MutableLiveData<Note?>()

    val notes = dataBase.getAllNotesIdOrdered()

    fun onAddNotePressed() {
        viewModelScope.launch {
            val newNote = Note()
            insert(newNote)
            actNote.value = getActNoteFromDatabase()
        }
    }

    fun onSaveNotePressed() {
        viewModelScope.launch {
            val oldNote = actNote.value ?: return@launch

            oldNote.title = "Note"

            update(oldNote)

            _navigateToEditNote.value = oldNote
        }
    }

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

    private val _navigateToEditNote = MutableLiveData<Note>()

    val navigateToEditNote: LiveData<Note>
        get() = _navigateToEditNote

//    fun doneNavigating() {
//        _navigateToEditNote.value = null
//    }

    init {
        initializeNote()
    }

    private fun initializeNote() {
        viewModelScope.launch {
            actNote.value = getActNoteFromDatabase()
        }
    }

    private suspend fun getActNoteFromDatabase(): Note? {
        var actNote = dataBase.getActNote()
        if (actNote?.title != "Note")
            actNote = null
        return actNote
    }

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