package hu.unideb.noteit.database

import java.util.*

data class Note(
    val id: Int,
    val noteName: String,
    val creationDate: Date,
    val category: String
)