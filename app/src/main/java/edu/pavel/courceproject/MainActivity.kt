package edu.pavel.courceproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.longToast
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var listData: ListView
    private lateinit var adapter: MyFilmsAdapter
    private lateinit var filmsTable: FilmsTable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listData = this.findViewById(R.id.listData)

        println("Start async")

        async(CommonPool) {
            filmsTable = FilmsTable(applicationContext)

            loadData(filmsTable)

            val films = filmsTable.selectAll()

            adapter = MyFilmsAdapter(applicationContext, films)

            runOnUiThread {
                listData.adapter = adapter
            }
        }

        println("End async")
    }

//TODO: Add load data for other request.
//TODO: Add detail activity for films.
//TODO: Add feature for save data in local DB.

    private fun loadData(filmsTable: FilmsTable) {
//        TODO("For db add feature filling the db from loaded data")

        val apiResponse: String = URL("https://ghibliapi.herokuapp.com/films").readText()

        val gson = Gson()
        val films: List<Film> = gson.fromJson(apiResponse, object : TypeToken<List<Film>>() {}.type)


        for (film in films) {
            filmsTable.insert(film)
        }

    }

    private fun arrayTitle(films: List<Film>): List<String> {
        val list = mutableListOf<String>()

        for (film in films) {
            list.add(film.title)
        }

        return list
    }


}
