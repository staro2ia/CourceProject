package edu.pavel.courceproject.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar
import android.widget.TextView
import edu.pavel.courceproject.R
import edu.pavel.courceproject.model.Film
import edu.pavel.courceproject.model.FilmsTable

import kotlinx.android.synthetic.main.activity_film.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @brief Class activity detail of film.
 */
class FilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        val id = intent.extras.getString(FilmsTable.Columns.id.string)?: return

        var film: Film
        var filmsTable: FilmsTable

        GlobalScope.launch {
            filmsTable = FilmsTable(this@FilmActivity)

            film = filmsTable.select(id)

            runOnUiThread {
                title = film.title

                val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
                val textViewYear = findViewById<TextView>(R.id.textViewYear)
                val textViewScore = findViewById<TextView>(R.id.textViewScore)
                val textViewDirector = findViewById<TextView>(R.id.textViewDirector)
                val textViewProducer = findViewById<TextView>(R.id.textViewProducer)
                val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
                val textViewURL = findViewById<TextView>(R.id.textViewURL)
                val ratingBar = findViewById<RatingBar>(R.id.ratingBar)

                textViewTitle.text = film.title
                textViewYear.text = film.release_date
                textViewScore.text = film.rt_score
                textViewDirector.text = film.director
                textViewProducer.text = film.producer
                textViewDescription.text = film.description
                textViewURL.text = film.url

                ratingBar.rating = film.rt_score.toFloat()
            }
        }

    }


    companion object {

        /**
         * @brief
         *
         * @param context – context
         * @param film – film, what id sends to intent.
         *
         * @return intent for call activity
         */
        fun newIntent(context: Context, film: Film): Intent {
            val detailIntent = Intent(context, FilmActivity::class.java)

            detailIntent.putExtra(FilmsTable.Columns.id.string, film.id)

            return detailIntent
        }
    }

}
