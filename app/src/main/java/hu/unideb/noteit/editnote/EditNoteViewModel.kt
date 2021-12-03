package hu.unideb.noteit.editnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.unideb.noteit.database.Note
import hu.unideb.noteit.database.NotesDatabaseDao
import kotlinx.coroutines.Job

class EditNoteViewModel(
    private val noteKey: Long = 0L,
    dataSource: NotesDatabaseDao) : ViewModel() {

    val dataBase = dataSource
    private val viewModelJob = Job()
    private val note: LiveData<Note>

    fun getNote() = note

    fun getNoteAsString(): String? {
        return note.value?.text
    }

    init {
        note = dataBase.getNoteWithId(noteKey)
    }

    private val _navigateToNotesFragment = MutableLiveData<Boolean?>()
    val navigateToNotesFragment: LiveData<Boolean?>
        get() = _navigateToNotesFragment

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToNotesFragment.value = null
    }

    fun onClose() {
        _navigateToNotesFragment.value = true
    }
}