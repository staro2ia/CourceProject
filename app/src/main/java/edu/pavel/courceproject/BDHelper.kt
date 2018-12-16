package edu.pavel.courceproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//TODO: Add DB helper

//TODO: Add films table helper

/**
 * @brief Data base version number
 */
const val dbVersion: Int = 1
/**
 * @brief Date base name string
 */
const val dbName: String = ""

/**
 * @brief Class helps open data base.
 */
class DBHelper(context: Context): SQLiteOpenHelper(context, dbName, null, dbVersion){
    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
CREATE TABLE '${FilmsTable.tableName}' (
    ${FilmsTable.Columns.id.string} INTEGER PRIMARY KEY AUTOINCREMENT,
    ${FilmsTable.Columns.title.string} TEXT,
    ${FilmsTable.Columns.description.string} TEXT,
    ${FilmsTable.Columns.director.string} TEXT,
    ${FilmsTable.Columns.producer.string} TEXT,
    ${FilmsTable.Columns.release_date.string} TEXT,
    ${FilmsTable.Columns.rt_score.string} TEXT,

    ${FilmsTable.Columns.url.string} TEXT
    );
        """.trimIndent()

        db?.execSQL(query)

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = """
DROP TABLE IF EXISTS '${FilmsTable.tableName}'
        """.trimIndent()

        db?.execSQL(query)
        onCreate(db)

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
