package edu.pavel.courceproject.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import edu.pavel.courceproject.R
import edu.pavel.courceproject.model.Film
import edu.pavel.courceproject.model.FilmsTable
import edu.pavel.courceproject.model.MyFilmsAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL


/**
 * @brief Class main application activity.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var listData: ListView
    private lateinit var adapter: MyFilmsAdapter
    private lateinit var filmsTable: FilmsTable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listData = this.findViewById(R.id.listData)

        GlobalScope.launch {
            filmsTable = FilmsTable(this@MainActivity)

            loadData(filmsTable)

            val films = filmsTable.selectAll()

            adapter = MyFilmsAdapter(applicationContext, films)

            runOnUiThread {
                listData.adapter = adapter
            }

            listData.setOnItemClickListener { _, _, position, _ ->
                val film = adapter.getItem(position)
                val detailIntent = FilmActivity.newIntent(this@MainActivity, film!!) // TODO: Исправить на нормально
                startActivity(detailIntent)
            }
        }

    }

//TODO: Add load data for other request.
//TODO: Add feature for save data in local DB.

    /**
     * @brief Load data from server and populate them to DB.
     *
     * @param filmsTable – table of films from BD
     *
     */
    private fun loadData(filmsTable: FilmsTable) {
        val apiResponse: String = URL("https://ghibliapi.herokuapp.com/films").readText()
        val gson = Gson()
        val films: List<Film> = gson.fromJson(apiResponse, object : TypeToken<List<Film>>() {}.type)

        for (film in films) {
            filmsTable.insert(film)
        }

    }

}

