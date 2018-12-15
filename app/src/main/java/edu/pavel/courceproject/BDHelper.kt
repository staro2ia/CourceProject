package edu.pavel.courceproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//TODO: Add DB helper

//todo: Add films table helper

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
