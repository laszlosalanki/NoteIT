package hu.unideb.noteit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo
    val title: String,
    @ColumnInfo(name = "creation_date")
    val creationDate: Date,
    @ColumnInfo
    val category: String
)