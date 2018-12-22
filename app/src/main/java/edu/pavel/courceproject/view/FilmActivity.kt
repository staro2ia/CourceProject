package edu.pavel.courceproject.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView
import edu.pavel.courceproject.R
import edu.pavel.courceproject.model.Film

import kotlinx.android.synthetic.main.activity_film.*

class FilmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val title = intent.extras.getString(EXTRA_TITLE)
        val url = intent.extras.getString(EXTRA_URL)

        setTitle(title)

        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)

        textViewTitle.text = title


    }


    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"

        fun newIntent(context: Context, film: Film): Intent {
            val detailIntent = Intent(context, FilmActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE, film.title)

            return detailIntent
        }
    }

}
