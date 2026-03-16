package edu.pavel.courceproject.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.pavel.courceproject.databinding.ActivityFilmBinding
import edu.pavel.courceproject.model.Film
import edu.pavel.courceproject.model.FilmsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @brief Class activity detail of film.
 */
class FilmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Детали фильма" // Установить заголовок
            setDisplayHomeAsUpEnabled(true) // Показать стрелку назад
        }

        val id = intent.extras?.getString(FilmsTable.Columns.id.string) ?: return

        lifecycleScope.launch {
            val film = withContext(Dispatchers.IO) {
                val filmsTable = FilmsTable(applicationContext)
                filmsTable.select(id)
            }

            val filmView = binding.include
            filmView.textViewTitle.text = film.title
            filmView.textViewYear.text = film.release_date
            filmView.textViewScore.text = film.rt_score
            filmView.textViewDirector.text = film.director
            filmView.textViewProducer.text = film.producer
            filmView.textViewDescription.text = film.description
            filmView.textViewURL.text = film.url
            filmView.ratingBar.rating = film.rt_score.toFloat()
        }
    }

    /**
     *
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
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

} // class FilmActivity
