package edu.pavel.courceproject

import android.graphics.Color
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.Loader
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.serialization.json.JSON
import kotlinx.serialization.*
import org.jetbrains.anko.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var listData : ListView
    private lateinit var adapter: MyFilmsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listData = this.findViewById(R.id.listData)

        longToast("Getting new data...")

         async(CommonPool) {
            val films = loadData()

            adapter = MyFilmsAdapter(applicationContext, films)

            runOnUiThread{
                println("Put data in UI")
                listData.adapter = adapter
            }
        }
    }

//TODO: Add load data for other request.
//TODO: Add detail activity for films.
//TODO: Add feature for save data in local DB.

     private  fun loadData(): List<Film> {
        val apiResponse: String = URL("https://ghibliapi.herokuapp.com/films").readText()

        println("Start parse apiResponse")

        val gson = Gson()
        val films: List<Film>  = gson.fromJson(apiResponse, object : TypeToken<List<Film>>() {}.type)

        println("Films list size = ${films.size}")
        println ("End parse apiResponse")

        return films
    }

    private fun arrayTitle(films: List<Film>): List<String> {
        val list = mutableListOf<String>()

        for ( film in films) {
            list.add(film.title)
        }

        return list
    }


}
