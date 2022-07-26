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
        val listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)

        contactDBHelper = SQLhelper(requireContext())
        db = contactDBHelper.readableDatabase
        val cursor:Cursor =db.rawQuery("SELECT * FROM contacts", null)
        cursor.moveToFirst()
        do {
            initRecyclerView()
        }while (cursor.moveToNext())
         return binding.root
    }
    fun initRecyclerView(){
        val manager = LinearLayoutManager(context)
        binding.rvContactList.layoutManager = manager
//        b.recyclerDog.addItemDecoration(decoration)
        binding.rvContactList  .layoutManager = LinearLayoutManager(context)
        binding.rvContactList.adapter = CotactAdapter(ContactProvider.contactList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}