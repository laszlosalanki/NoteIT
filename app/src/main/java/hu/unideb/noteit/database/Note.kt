package hu.unideb.noteit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "title")
    val title: String = "Note",
    @ColumnInfo(name = "creation_time")
    val creationTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "category")
    val category: String = "Base"
)