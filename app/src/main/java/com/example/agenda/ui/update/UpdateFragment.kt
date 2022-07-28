package com.example.agenda.ui.update

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.agenda.ContactData
import com.example.agenda.GetSpinner
import com.example.agenda.SQLhelper
import com.example.agenda.databinding.FragmentUpdateBinding
import com.example.agenda.hideKeyboard

class UpdateFragment : Fragment() {

    private var _b: FragmentUpdateBinding? = null
    private val b get() = _b!!
    lateinit var contactDBHelper: SQLhelper
    private var spnOpt = arrayOf("")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val updateViewModel =
            ViewModelProvider(this).get(UpdateViewModel::class.java)
        _b = FragmentUpdateBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactDBHelper = SQLhelper(context)
        GetSpinner(b.spnUpdId, spnOpt, sqlToArray())

        b.btnUpdContact.setOnClickListener {
            val endIndex = spnOpt[0].indexOf(':')
            val id = spnOpt[0].substring(0, endIndex)
            val affectedRows = contactDBHelper.updateData(id,
                b.etUpdName.text.toString(),
                b.etUpdSurname.text.toString(),
                b.etUpdMail.text.toString(),
                b.edUpdPhone.text.toString())

            b.etUpdName.text.clear()
            b.etUpdSurname.text.clear()
            b.etUpdMail.text.clear()
            b.edUpdPhone.text.clear()
            GetSpinner(b.spnUpdId, spnOpt, sqlToArray())
            if (affectedRows > 0) {
                Toast.makeText(context,"Has modificado $affectedRows registros", Toast.LENGTH_SHORT).show()
                hideKeyboard()
            } else {
                Toast.makeText(context,"No se han modificado registros", Toast.LENGTH_SHORT).show()
            }
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