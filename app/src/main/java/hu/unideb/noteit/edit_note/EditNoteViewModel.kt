package hu.unideb.noteit.edit_note

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.unideb.noteit.database.Note
import hu.unideb.noteit.database.NotesDatabaseDao

class EditNoteViewModel(
    dataSource: NotesDatabaseDao,
    application: Application
) : ViewModel() {

    val dataBase = dataSource
    val notes = dataBase.getAllNotesIdOrdered()

}