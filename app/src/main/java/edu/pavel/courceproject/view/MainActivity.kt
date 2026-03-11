package edu.pavel.courceproject.view

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.pavel.courceproject.R
import edu.pavel.courceproject.model.FilmsTable
import edu.pavel.courceproject.model.MyFilmsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * @brief Class main application activity.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var listData: ListView
    private lateinit var adapter: MyFilmsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listData = this.findViewById(R.id.listData)

        lifecycleScope.launch {
            val filmsTable = FilmsTable(this@MainActivity)

            val films = withContext(Dispatchers.IO) {
                filmsTable.loadData()
                filmsTable.selectAll()
            }

            adapter = MyFilmsAdapter(applicationContext, films)
            listData.adapter = adapter

            listData.setOnItemClickListener { _, _, position, _ ->
                adapter.getItem(position)?.let { film ->
                    val detailIntent = FilmActivity.newIntent(this@MainActivity, film)
                    startActivity(detailIntent)
                }
            }
        }
    }

//TODO: Add load data for other request.
//TODO: Add feature for save data in local DB.

}

