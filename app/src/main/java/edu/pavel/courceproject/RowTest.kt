package edu.pavel.courceproject

import android.content.Context
import android.widget.TableRow
import kotlinx.serialization.*

class RowTest (context: Context) : TableRow (context) {

}

@Serializable
data class Film (
    val id: String,
    val title: String,
    val description: String,
    val director: String,
    val producer: String,
    val release_date: String,
    val rt_score: String,
    val people: List<String>,
    val species: List<String>,
    val locations: List<String>,
    val vehicles: List<String>,
    val url: String

)