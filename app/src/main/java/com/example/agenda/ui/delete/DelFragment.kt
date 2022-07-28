package com.example.agenda.ui.delete

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.agenda.GetSpinner
import com.example.agenda.R
import com.example.agenda.SQLhelper
import com.example.agenda.databinding.FragmentDelBinding
import com.example.agenda.databinding.FragmentUpdateBinding
import com.example.agenda.hideKeyboard


class DelFragment : Fragment() {
    private var _b: FragmentDelBinding? = null
    private val b get() = _b!!
    lateinit var contactDBHelper: SQLhelper
    private var spnOpt = arrayOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentDelBinding.inflate(inflater, container, false)
        return b.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactDBHelper = SQLhelper(context)
        GetSpinner(b.spnDelContact, spnOpt, sqlToArray())

        b.btnDelContact.setOnClickListener {
            val endIndex = spnOpt[0].indexOf(':')
            contactDBHelper.deleteData(spnOpt[0].toString().substring(0, endIndex))
            GetSpinner(b.spnDelContact, spnOpt, sqlToArray())
            hideKeyboard()
        }
    }


    fun sqlToArray():Array<String>{
        val contactos = arrayListOf<String>()
        val db : SQLiteDatabase = contactDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${SQLhelper.TABLE_NAME}", null)

        // Compruebo si hay algún registro
        if (cursor.moveToFirst()) {
            do {
                contactos.add(cursor.getInt(0).toString() + ": " +
                        cursor.getString(1) + " " + cursor.getString(2))

            } while (cursor.moveToNext())
        }
        cursor.close()
        return contactos.toTypedArray()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}