package edu.pavel.courceproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.serialization.*
import java.util.ArrayList


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

/**
 * @brief Adapter for ListView of Film
 */
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

/**
 * @brief
 */
class FilmsTable {
    companion object {
        val tableName = "Films"
    }

    /**
     * @brief Enumeration for columns of Films table.
     */
    enum class Columns (val string: String, val number: Int) {
        id ("ID", 0),
        title ("Title", 1),
        description ("Description", 2),
        director ("Director", 3),
        producer ("Producer", 4),
        release_date ("Release_date", 5),
        rt_score ("RT_score", 6),
        people ("People", 7),
        species ("Species", 8),
        locations ("Locations", 9),
        vehicles ("Vehicles", 10),
        url ("URL", 11)
    }

    /**
     * @brief
     */
    private val  db: SQLiteDatabase

    constructor(context: Context) {
        println("Start FilmsTable::constructor")
        val dbHelper = DBHelper(context)
        db = dbHelper.writableDatabase
        println("End FilmsTable::constructor")
    }

    fun insert(film: Film): Long {
        val cv = ContentValues()
        cv.put(Columns.id.string, film.id)
        cv.put(Columns.title.string, film.title)
        cv.put(Columns.description.string, film.description)
        cv.put(Columns.director.string, film.director)
        cv.put(Columns.producer.string, film.producer)
        cv.put(Columns.release_date.string, film.release_date)
        cv.put(Columns.rt_score.string, film.rt_score)
//        cv.put(Columns.people.string, film.people)
//        cv.put(Columns.species.string, film.species)
//        cv.put(Columns.locations.string, film.locations)
//        cv.put(Columns.vehicles.string, film.vehicles)
        cv.put(Columns.url.string, film.url)


        return db.insert(tableName, null, cv)
//        TODO ("not implemented")
    }

//    fun update(x: Film): Int {
//        val cv = ContentValues()
//        cv.put(Columns.title.string, x.title)
//        return db.update(tableName, cv, " '${Columns.id.string}' = ? ", arrayOf<String>(x.id))
//        TODO ("not implemented")
//    }
//
//    fun deleteAll() {
//        db.delete(tableName, null, null)
//        TODO ("not implemented")
//    }
//
//    fun delete(id: Long) {
//        db.delete(tableName, " '${Columns.id.string}' = ? ", arrayOf(id.toString()))
//        TODO ("not implemented")
//    }

    fun select(id: String): Film {
        val cursor = db.query(tableName, null,
            " '${Columns.id.string}' = ? ", arrayOf(id),
            null, null, null)

        cursor.moveToFirst()
        val title = cursor.getString(Columns.title.number)
        val description = cursor.getString(Columns.description.number)
        val director = cursor.getString(Columns.director.number)
        val producer = cursor.getString(Columns.producer.number)
        val release_date = cursor.getString(Columns.release_date.number)
        val rt_score = cursor.getString(Columns.rt_score.number)
        val people = cursor.getString(Columns.people.number)
        val species = cursor.getString(Columns.species.number)
        val locations = cursor.getString(Columns.locations.number)
        val vehicles = cursor.getString(Columns.locations.number)
        val url = cursor.getString(Columns.url.number)

        return Film(id, title, description, director, producer, release_date, rt_score,
            listOf(), listOf(), listOf(), listOf(),
            url)
    }


    fun selectAll(): ArrayList<Film> {
        val cursor = db.query(tableName, null, null, null, null, null, null)

        val arr = ArrayList<Film>()
        cursor.moveToFirst()
        if (!cursor.isAfterLast) {
            do {
                val id = cursor.getString(Columns.id.number)
                val title = cursor.getString(Columns.title.number)
                val description = cursor.getString(Columns.description.number)
                val director = cursor.getString(Columns.director.number)
                val producer = cursor.getString(Columns.producer.number)
                val release_date = cursor.getString(Columns.release_date.number)
                val rt_score = cursor.getString(Columns.rt_score.number)
                val people = cursor.getString(Columns.people.number)
                val species = cursor.getString(Columns.species.number)
                val locations = cursor.getString(Columns.locations.number)
                val vehicles = cursor.getString(Columns.locations.number)
                val url = cursor.getString(Columns.url.number)

                arr.add(Film(id, title, description, director, producer, release_date, rt_score,
                    listOf(), listOf(), listOf(), listOf(),
                    url))
            } while (cursor.moveToNext())
        }
        return arr
//        TODO ("not implemented")
    }

}