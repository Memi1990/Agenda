package com.example.agenda.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.ContactProvider
import com.example.agenda.adapter.CotactAdapter
import com.example.agenda.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)
        initRecyclerView()
         return binding.root
    }
    fun initRecyclerView(){
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, manager.orientation)
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