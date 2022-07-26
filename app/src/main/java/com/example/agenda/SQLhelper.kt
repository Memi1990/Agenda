package com.example.agenda

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.agenda.ui.list.ListFragment
import com.example.agenda.ui.newcontact.NewFragment

class SQLhelper(context: Context?): SQLiteOpenHelper(context, DATABASE, null, 1) {

    companion object {
        const val DATABASE = "AddessBook"
        const val TABLE_NAME ="contacts"
        const val FIELD_ID="_id"
        const val FIELD_NAME="name"
        const val FIELD_SURNAME="surname"
        const val FIELD_MAIL="mail"
        const val FIELD_PHONE="phonee"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val commandCreate = "CREATE TABLE $TABLE_NAME"+
                "($FIELD_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$FIELD_NAME TEXT," +
                "$FIELD_SURNAME TEXT,"+
                "$FIELD_MAIL TEXT,"+
                "$FIELD_PHONE TEXT)"
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
        data.put(FIELD_NAME, name)
        data.put(FIELD_SURNAME, surname)
        data.put(FIELD_MAIL, email)
        data.put(FIELD_PHONE, phone)
        // Abro la DB en modo ESCRITURA
        val db = this.writableDatabase
        db.insert("contacts", null,data)
        db.close()
    }
    fun updateData (id:String, name: String, last_name: String, email: String, phone: String) : Int {
        val args = arrayOf(id)

        // ContentValues tiene una estructura de tipo Map()
        val data = ContentValues()
        data.put(FIELD_NAME, name)
        data.put(FIELD_SURNAME, last_name)
        data.put(FIELD_MAIL, email)
        data.put(FIELD_PHONE, phone)
        // Abro la DB en modo ESCRITURA
        val db = this.writableDatabase
        // La ejecución de este comando devuelve el número de registros afectados
        val affectedRows = db.update(TABLE_NAME, data, "_id = ?",args)
        db.close()
        return affectedRows
    }

    fun deleteData (id: String) : Int {
        val args = arrayOf(id)

        val db = this.writableDatabase
        // La ejecución de este comando devuelve el número de registros afectados
        val affectedRows = db.delete(TABLE_NAME, "_id = ?",args)
        // Alternativamente. Pero esta forma no devuelve nada
        // db.execSQL("DELETE FROM friends WHERE _id = ?", args)
        db.close()
        return affectedRows
    }

}