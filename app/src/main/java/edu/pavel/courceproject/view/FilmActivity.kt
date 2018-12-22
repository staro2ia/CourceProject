package edu.pavel.courceproject.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView
import edu.pavel.courceproject.R
import edu.pavel.courceproject.model.Film
import edu.pavel.courceproject.model.FilmsTable

import kotlinx.android.synthetic.main.activity_film.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        val id = intent.extras.getString(FilmsTable.Columns.id.string)

        var film: Film
        var filmsTable: FilmsTable

        GlobalScope.launch {
            filmsTable = FilmsTable(this@FilmActivity)



            film = filmsTable.select(id)

            runOnUiThread {
                val title = film.title
                val url = film.url

                setTitle(title)

                val textViewTitle = findViewById<TextView>(R.id.textViewTitle)

                textViewTitle.text = title
            }
        }




    }


    companion object {

        fun newIntent(context: Context, film: Film): Intent {
            val detailIntent = Intent(context, FilmActivity::class.java)

            detailIntent.putExtra(FilmsTable.Columns.id.string, film.id)

            return detailIntent
        }
    }

}
