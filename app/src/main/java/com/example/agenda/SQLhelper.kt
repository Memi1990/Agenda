package com.example.agenda

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.agenda.ui.list.ListFragment
import com.example.agenda.ui.newcontact.NewFragment

class SQLhelper(context: Context?): SQLiteOpenHelper(context, "AddressBook.db", null, 1) {
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
    fun addData (name: String, surname:String, email: String, phone:String ) {
        // ContentValues tiene una estructura de tipo Map()
        val data = ContentValues()
        data.put("name", name)
        data.put("surname", surname)
        data.put("email", email)
        data.put("phone", phone)
        // Abro la DB en modo ESCRITURA
        val db = this.writableDatabase
        db.insert("contacts", null,data)
        db.close()
    }

}