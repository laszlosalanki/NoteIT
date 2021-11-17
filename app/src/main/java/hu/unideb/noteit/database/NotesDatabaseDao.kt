package hu.unideb.noteit.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDatabaseDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM notes_table WHERE id = :id")
    suspend fun get(id: Long): Note?

    @Query("SELECT * FROM notes_table ORDER BY id")
    suspend fun getAllNotesIdOrdered(): LiveData<List<Note>>

    @Query("SELECT * FROM notes_table ORDER BY title ASC")
    suspend fun getAllNotesTitleOrderedAsc(): LiveData<List<Note>>

    @Query("SELECT * FROM notes_table ORDER BY title DESC")
    suspend fun getAllNotesTitleOrderedDesc(): LiveData<List<Note>>

    @Query("DELETE FROM notes_table")
    suspend fun clear()

    @Query("DELETE FROM notes_table WHERE id = :id")
    suspend fun deleteById(id: Long)
}