package com.example.agenda.ui.newcontact

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.agenda.SQLhelper
import com.example.agenda.databinding.FragmentNewBinding
import com.example.agenda.hideKeyboard

class NewFragment : Fragment() {

    private var _binding: FragmentNewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var contactDBHelper : SQLhelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newViewModel =
            ViewModelProvider(this).get(NewViewModel::class.java)

        _binding = FragmentNewBinding.inflate(inflater, container, false)
        contactDBHelper= SQLhelper(context)

        binding.btnAddContact.setOnClickListener {
            hideKeyboard()
            if (binding.etName.text.isNotBlank() &&
                binding.etSurname.text.isNotBlank()&&
                binding.etMail.text.isNotBlank()&&
                binding.edPhone.text.isNotBlank()){
                contactDBHelper.addData(binding.etName.text.toString(),
                    binding.etSurname.text.toString(),
                    binding.etMail.text.toString(),
                    binding.edPhone.text.toString())
                binding.etName.text.clear()
                binding.etSurname.text.clear()
                binding.etMail.text.clear()
                binding.edPhone.text.clear()

                Toast.makeText(context,"¡Guardado!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
            } else {
                Toast.makeText(context, "No se ha podido guardar", Toast.LENGTH_SHORT).show()
            }
            hideKeyboard()
        }
        return binding.root
    }
//    fun hideKeyBoard() {
//        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(binding.relativeLayout.windowToken, 0)
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}