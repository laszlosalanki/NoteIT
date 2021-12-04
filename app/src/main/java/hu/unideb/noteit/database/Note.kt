package hu.unideb.noteit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "title")
    var title: String = "Note",
    @ColumnInfo(name = "creation_time")
    var creationTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "category")
    var category: String = "Base",
    @ColumnInfo(name = "text")
    var text: String = "Blank"
)