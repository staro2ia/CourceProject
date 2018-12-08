package edu.pavel.courceproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TableRow
import android.widget.TextView
import kotlinx.serialization.*


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

//TODO: Make beautiful list row.
//TODO: Add important field to row.



class MyFilmsAdapter (context: Context, list: List<Film>)
    : ArrayAdapter<Film>(context, R.layout.test_data_row, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val film = getItem(position)!!
        println("$film")

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.test_data_row, parent, false)
        view.findViewById<TextView>(R.id.textViewFilm).text = film.title

        println ("In film $position Title ${film.title} ")

        return view
    }

}
