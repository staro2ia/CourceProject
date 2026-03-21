package edu.pavel.courceproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.pavel.courceproject.R
import edu.pavel.courceproject.databinding.ActivityMainBinding
import edu.pavel.courceproject.model.FilmsTable
import edu.pavel.courceproject.model.MyFilmsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @brief Class main application activity.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyFilmsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = getString(R.string.app_name)
        }

        lifecycleScope.launch {
            val filmsTable = FilmsTable(this@MainActivity)

            val films = withContext(Dispatchers.IO) {
                filmsTable.loadData()
                filmsTable.selectAll()
            }

            adapter = MyFilmsAdapter(applicationContext, films)
            binding.listData.adapter = adapter

            binding.listData.setOnItemClickListener { _, _, position, _ ->
                adapter.getItem(position)?.let { film ->
                    val detailIntent = FilmActivity.newIntent(this@MainActivity, film)
                    startActivity(detailIntent)
                }
            }
        }
    }

} // class MainActivity

