package edu.pavel.courceproject.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.pavel.courceproject.model.Film
import edu.pavel.courceproject.model.FilmsTable
import edu.pavel.courceproject.model.MyFilmsAdapter
import edu.pavel.courceproject.R
import kotlinx.coroutines.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import java.net.URL

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
            listData.setOnItemClickListener { parent, view, position, id ->
                val film = adapter.getItem(position)

                toast("Film is ${film.title}")

                val detailIntent = FilmActivity.newIntent(this@MainActivity, film)
                startActivity(detailIntent)
            }
        }


    }

//TODO: Add load data for other request.
//TODO: Add detail activity for films.
//TODO: Add feature for save data in local DB.

    /**
     * @brief Load data from server and populate them to DB.
     */
    private fun loadData(filmsTable: FilmsTable) {
//        TODO("For db add feature filling the db from loaded data")

        val apiResponse: String = URL("https://ghibliapi.herokuapp.com/films").readText()

        val gson = Gson()
        val films: List<Film> = gson.fromJson(apiResponse, object : TypeToken<List<Film>>() {}.type)


        for (film in films) {
            filmsTable.insert(film)
        }

    }

}

