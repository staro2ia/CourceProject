package edu.pavel.courceproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import java.net.URL

class MainActivity : AppCompatActivity() {

    var countRow = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val table = findViewById<TableLayout>(R.id.tableMain)

        doAsync {
            val apiResponse = (URL("https://ghibliapi.herokuapp.com/#tag/Films").readText())

            println(apiResponse)
        }

        countRow += 5

        for( i in 0 until countRow ) {
            val row = TableRow(this)
            row.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)

            val text = TextView(this)
            text.text = i.toString()

            row.addView(text, 0)

            table.addView(row, i)
        }

    }
}
