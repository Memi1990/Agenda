package com.example.agenda.adapter

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.ContactData
import com.example.agenda.ContactProvider.Companion.contactList
import com.example.agenda.R

class CotactAdapter(private val contactList:List<ContactData>): RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(layoutInflater.inflate(R.layout.recylcer_list, parent,false))
    }
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = contactList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = contactList.size



}