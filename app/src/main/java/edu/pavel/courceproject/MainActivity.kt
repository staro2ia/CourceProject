package edu.pavel.courceproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.Loader
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.experimental.async
import kotlinx.serialization.json.JSON
import kotlinx.serialization.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var countRow = 0
    private var films: List<Film> = listOf()
//    private var films: MutableList<Film> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val table = findViewById<TableLayout>(R.id.tableMain)

        async {
            val apiResponse: String = URL("https://ghibliapi.herokuapp.com/films").readText()

            val testFilmString = """
 {
 "id": "2baf70d1-42bb-4437-b551-e5fed5a87abe",
 "title": "Castle in the Sky",
 "description": "The orphan Sheeta inherited a mysterious crystal that links her to the mythical sky-kingdom of Laputa. With the help of resourceful Pazu and a rollicking band of sky pirates, she makes her way to the ruins of the once-great civilization. Sheeta and Pazu must outwit the evil Muska, who plans to use Laputa's science to make himself ruler of the world.",
 "director": "Hayao Miyazaki",
 "producer": "Isao Takahata",
 "release_date": "1986",
 "rt_score": "95",
 "people": [
 "https://ghibliapi.herokuapp.com/people/"
 ],
 "species": [
 "https://ghibliapi.herokuapp.com/species/af3910a6-429f-4c74-9ad5-dfe1c4aa04f2"
 ],
 "locations": [
 "https://ghibliapi.herokuapp.com/locations/"
 ],
 "vehicles": [
 "https://ghibliapi.herokuapp.com/vehicles/"
 ],
 "url": "https://ghibliapi.herokuapp.com/films/2baf70d1-42bb-4437-b551-e5fed5a87abe"
 }
 """

            println("Start parse apiResponse")

//            films = JSON.parse(apiResponse)

            val film = JSON.parse< Film >(apiResponse)
//            val gson = Gson()
//            films = gson.fromJson(apiResponse, object : TypeToken<List<Film>>() {}.type)

            println("Films list?")
//            println(films.size)
            println(film)
        }


        countRow += 5

        for (i in 0 until countRow) {
            val row = TableRow(this)
            row.layoutParams =
                    TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)

            val text = TextView(this)
            text.text = i.toString()

            row.addView(text, 0)

            table.addView(row, i)
        }

    }
}
