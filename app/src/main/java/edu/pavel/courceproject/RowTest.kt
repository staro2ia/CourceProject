package edu.pavel.courceproject

import android.content.Context
import android.widget.TableRow

class RowTest (context: Context) : TableRow (context) {

}


data class Film (
    val id: String,
    val title: String,
    val description: String,
    val director: String,
    val producer: String,
    val release_date: String,
    val rt_score: String
)