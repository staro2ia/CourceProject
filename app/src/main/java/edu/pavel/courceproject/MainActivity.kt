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

    private var countRow = 0
//    private var films: List<Film> = listOf()

    private lateinit var listData : ListView
    private lateinit var adapter: MyFilmsAdapter

//    private var films: MutableList<Film> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listData = findViewById(R.id.listData)

        longToast("Getting new data...")

//  TODO Async load data and polute ListView
        async(CommonPool) {

            val films = loadData()

            adapter = MyFilmsAdapter(applicationContext, films)

//        val array = listOf("Tets 1", "Test 2", "Test 3")

//        listData.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, array)
//            listData.backgroundColor = Color.parseColor("#0000FF")
            runOnUiThread{
                println("Put data in UI")
                listData.adapter = adapter
//                adapter.notifyDataSetChanged()
            }
        }


    }

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
        var list = mutableListOf<String>()

        for ( film in films) {
            list.add(film.title)
        }

        return list
    }


}
