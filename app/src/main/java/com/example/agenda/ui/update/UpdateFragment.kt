package com.example.agenda.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.agenda.GetSpinner
import com.example.agenda.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private var _b: FragmentUpdateBinding? = null
    private val b get() = _b!!
//    private lateinit var spnOpt = ArrayList<>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val updateViewModel =
            ViewModelProvider(this).get(UpdateViewModel::class.java)
        _b = FragmentUpdateBinding.inflate(inflater, container, false)
//        GetSpinner(context,b.spnUpdId,)
        return b.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}