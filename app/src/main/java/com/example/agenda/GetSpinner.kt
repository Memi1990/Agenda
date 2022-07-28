package com.example.agenda

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class GetSpinner {
    private var _spinner:Spinner
    private var _selectedOption = arrayOf("")

    constructor(spinner: Spinner, selectedOption:Array<String>, idVals:Int) {
        this._spinner = spinner
        this._selectedOption = selectedOption
        val mAdapter = ArrayAdapter.createFromResource(spinner.context, idVals, android.R.layout.simple_spinner_item)
        this._spinner.adapter = mAdapter
        setOnSelect()
    }
    constructor(spinner: Spinner, selectedOption:Array<String>, arrVals:Array<String>) {
        this._spinner = spinner
        this._selectedOption = selectedOption
        val mAdapter = ArrayAdapter(spinner.context, android.R.layout.simple_spinner_item, arrVals)
        this._spinner.adapter = mAdapter
        setOnSelect()
    }

    fun setOnSelect(){
        _spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                _selectedOption[0] = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                _selectedOption[0] = "Sin selección"
            }
        }
    }
}