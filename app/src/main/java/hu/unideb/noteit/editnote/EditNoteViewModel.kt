package hu.unideb.noteit.editnote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.unideb.noteit.database.Note
import hu.unideb.noteit.database.NotesDatabaseDao
import hu.unideb.noteit.databinding.FragmentEditNoteBinding
import hu.unideb.noteit.network.Comic
import hu.unideb.noteit.network.ComicApi
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.*

class EditNoteViewModel(
    private val noteKey: Long = 0L,
    dataSource: NotesDatabaseDao,
    val binding: FragmentEditNoteBinding) : ViewModel() {

    val dataBase = dataSource
    private val note: LiveData<Note>

    fun getNote() = note

    init {
        note = dataBase.getNoteWithId(noteKey)
    }

    fun getComics() {
        viewModelScope.launch {
            val comic: Comic = ComicApi.retrofitService.getProperties()
            val toEditText = comic.alt + '\n' + comic.img
            binding.editTextNote.setText(toEditText)
        }
    }

    private val _navigateToNotesFragment = MutableLiveData<Boolean?>()
    val navigateToNotesFragment: LiveData<Boolean?>
        get() = _navigateToNotesFragment

    fun doneNavigating() {
        _navigateToNotesFragment.value = null
    }

    private suspend fun update(note: Note) {
        dataBase.update(note)
    }

    private suspend fun delete(id: Long)
    {
        dataBase.deleteById(id)
    }

    fun saveThings() {
        viewModelScope.launch {
            val actNote = dataBase.get(noteKey)
            actNote?.title = binding.editTextTitle.text.toString()
            actNote?.category = binding.editTextCategories.text.toString()
            actNote?.text = binding.editTextNote.text.toString()
            actNote?.creationTime = System.currentTimeMillis()

            if (actNote != null) {
                update(actNote)
            }
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            delete(noteKey)
            _navigateToNotesFragment.value = true
        }
    }

}