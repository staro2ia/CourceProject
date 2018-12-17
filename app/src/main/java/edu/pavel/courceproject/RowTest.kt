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

    val  db: SQLiteDatabase

    constructor(context: Context) {
        val dbHelper = DBHelper(context)
        db = dbHelper.writableDatabase
    }

    fun insert(x: Film): Long {
        val cv = ContentValues()
        cv.put(Columns.id.string, x.id)
        return db.insert(tableName, null, cv)
//TODO ("not implemented")
    }

    fun update(x: Film): Int {
        val cv = ContentValues()
        cv.put(Columns.title.string, x.title)
        return db.update(tableName, cv, " '${Columns.id.string}' = ? ", arrayOf<String>(x.id))
        TODO ("not implemented")
    }

    fun deleteAll() {
        db.delete(tableName, null, null)
        TODO ("not implemented")
    }

    fun delete(id: Long) {
        db.delete(tableName, " '${Columns.id.string}' = ? ", arrayOf(id.toString()))
        TODO ("not implemented")
    }

    fun select(id: Long): Film {
        val mCursor = db.query(tableName, null,
            " '${Columns.id.string}' = ? ", arrayOf(id.toString()),
            null, null, null)

        mCursor.moveToFirst()
        val title = mCursor.getString(Columns.title.number)
        return Film(id, title )
        TODO ("not implemented")
    }

    fun selectAll(): ArrayList<Film> {
        val mCursor = db.query(tableName, null, null, null, null, null, null)

        val arr = ArrayList<Film>()
        mCursor.moveToFirst()
        if (!mCursor.isAfterLast) {
            do {
                val id = mCursor.getString(Columns.id.number)
                val title = mCursor.getString(Columns.title.number)

                arr.add(Film(id, title))
            } while (mCursor.moveToNext())
        }
        return arr
        TODO ("not implemented")
    }

}