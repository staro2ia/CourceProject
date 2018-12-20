package edu.pavel.courceproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


//TODO: Add DB helper

//TODO: Add films table helper



/**
 * @brief Class helps open data base.
 */
class DBHelper(context: Context): SQLiteOpenHelper(context, dbName, null, dbVersion){

    override fun onCreate(db: SQLiteDatabase?) {

        println("Start DBHelper::onCreate")
        val query = """
CREATE TABLE IF NOT EXISTS `Vehicles` (
	`ID`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `Species` (
	`ID`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `People` (
	`ID`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `Locations` (
	`ID`	TEXT NOT NULL UNIQUE,
	PRIMARY KEY(`ID`)
);

CREATE TABLE IF NOT EXISTS `Films` (
	`${FilmsTable.Columns.id.string}`	TEXT NOT NULL UNIQUE,
	`${FilmsTable.Columns.title.string}`	TEXT NOT NULL,
	`${FilmsTable.Columns.description.string}`	TEXT,
	`${FilmsTable.Columns.director.string}`	TEXT NOT NULL,
	`${FilmsTable.Columns.producer.string}`	TEXT NOT NULL,
	`${FilmsTable.Columns.release_date.string}`	TEXT,
	`${FilmsTable.Columns.rt_score.string}`	TEXT,
	`${FilmsTable.Columns.people.string}`	TEXT,
	`${FilmsTable.Columns.species.string}`	TEXT,
	`${FilmsTable.Columns.locations.string}`	TEXT,
	`${FilmsTable.Columns.vehicles.string}`	TEXT,
	`${FilmsTable.Columns.url.string}`	TEXT NOT NULL,
	PRIMARY KEY(`${FilmsTable.Columns.id.string}`),
    FOREIGN KEY(`${FilmsTable.Columns.people.string}`) REFERENCES `People`(`ID`),
	FOREIGN KEY(`${FilmsTable.Columns.species.string}`) REFERENCES `Species`(`ID`),
	FOREIGN KEY(`${FilmsTable.Columns.locations.string}`) REFERENCES `Locations`(`ID`),
	FOREIGN KEY(`${FilmsTable.Columns.vehicles.string}`) REFERENCES `Vehicles`(`ID`)
);
        """.trimIndent()

        db?.execSQL(query)

        println("End DBHelper::onCreate")
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = """
DROP TABLE IF EXISTS '${FilmsTable.tableName}'
        """.trimIndent()

        db?.execSQL(query)
        onCreate(db)

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        /**
         * @brief Data base version number
         */
        const val dbVersion: Int = 1
        /**
         * @brief Date base name string
         */
        const val dbName: String = ""
    }
}
