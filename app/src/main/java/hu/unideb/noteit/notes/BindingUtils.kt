package hu.unideb.noteit.notes

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.unideb.noteit.database.Note
import java.text.DateFormat

@BindingAdapter("title")
fun TextView.setTitle(item: Note) {
    text = item.title
}

@BindingAdapter("creationDate")
fun TextView.setCreationDate(item: Note) {
    text = DateFormat.getDateTimeInstance().format(item.creationTime)
}

@BindingAdapter("category")
fun TextView.setCategory(item: Note) {
    text = item.category
}