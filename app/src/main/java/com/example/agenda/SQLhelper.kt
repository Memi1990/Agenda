package com.example.agenda

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLhelper(context:Context): SQLiteOpenHelper(context, "AddressBook.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val commandCreate = "CREATE TABLE contacts"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "surname TEXT,"+
                "email TEXT,"+
                "phone TEXT,"+
                "picture NUMBER)"
        db!!.execSQL(commandCreate)

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val commandDelete = "DROP TABLE IF EXISTS contacts"
        db!!.execSQL(commandDelete)
        onCreate(db)
    }

}