package hu.unideb.noteit.notes

import android.text.Editable
import android.widget.EditText
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

@BindingAdapter("editNoteTitle")
fun EditText.setTitle(item: Note?) {
    text = item?.title?.toEditable()
}

@BindingAdapter("editNoteCategory")
fun EditText.setCategory(item: Note?) {
    text = item?.category?.toEditable()
}

@BindingAdapter("editNoteText")
fun EditText.setText(item: Note?) {
    text = item?.text?.toEditable()
}

private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)