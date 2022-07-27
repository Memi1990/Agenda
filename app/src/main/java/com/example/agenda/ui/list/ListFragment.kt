package com.example.agenda.ui.list

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.ContactData
import com.example.agenda.ContactProvider
import com.example.agenda.SQLhelper
import com.example.agenda.adapter.CotactAdapter
import com.example.agenda.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var contactDBHelper: SQLhelper
    private lateinit var db: SQLiteDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactDBHelper = SQLhelper(requireContext())
        initRecyclerView()
    }

    fun initRecyclerView(){
        val manager = LinearLayoutManager(context)
        binding.rvContactList.layoutManager = manager
//        b.recyclerDog.addItemDecoration(decoration)
        binding.rvContactList  .layoutManager = LinearLayoutManager(context)
        binding.rvContactList.adapter = CotactAdapter(sqliteToList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun sqliteToList():MutableList<ContactData> {
        val registros = mutableListOf<ContactData>()
        // Abro la base de datos en modo LECTURA
        val db : SQLiteDatabase = contactDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${SQLhelper.TABLE_NAME}", null)

        // Compruebo si hay algún registro
        if (cursor.moveToFirst()) {
            do {
                registros.add(
                    ContactData(
                        cursor.getInt(0).toString(),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return registros
    }
}