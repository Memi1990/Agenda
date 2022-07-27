package com.example.agenda.adapter

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.ContactData
import com.example.agenda.databinding.ActivityMainBinding.bind
import com.example.agenda.databinding.RecylcerListBinding

class ContactViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val b = RecylcerListBinding.bind(view)

    fun render(contactModel: ContactData){
        b.tvId.text = "ID: ".plus(contactModel.id)
        b.tvListName.text = "Nombre: ".plus(contactModel.name)
        b.tvListEmail.text = "Email: "+contactModel.email
        b.tvListPhone.text = "Teléfono: "+contactModel.phone

    }
}