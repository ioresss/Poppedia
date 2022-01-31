package tads.eaj.ufrn.poppedia.fragments.insert

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import tads.eaj.ufrn.poppedia.R
import tads.eaj.ufrn.poppedia.data.Celeb
import tads.eaj.ufrn.poppedia.fragments.dialogs.InsertDialog
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class InsertFragment : Fragment() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_insert, container, false)

        val insertViewModel = ViewModelProvider(this).get(InsertViewModel :: class.java)

        view.findViewById<Button>(R.id.btnEdit).setOnClickListener{
            val name = view.findViewById<EditText>(R.id.txtName)?.text.toString()
            val occupation =view.findViewById<EditText>(R.id.txtEditOccupation)?.text.toString()
            val birthdate = view.findViewById<EditText>(R.id.txtBirthDate)?.text.toString()
            val description = view.findViewById<EditText>(R.id.txtDescription)?.text.toString()
            val website = view.findViewById<EditText>(R.id.txtWebsite)?.text.toString()
            if(validate(name, occupation, birthdate, description, website)){
                val celeb = Celeb(0, name, SimpleDateFormat("dd/MM/yyyy").parse(birthdate) , occupation, description, website)
                insertViewModel.insertCeleb(celeb)
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_insertFragment_to_listFragment)
            }
            else{
                Toast.makeText(requireContext(), "Cannot add new celebrity. Please, fill out all fields!", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> {
                val dialog = InsertDialog()
                dialog.show(requireActivity().supportFragmentManager, "InsertDialog")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validate(name:String, occupation: String, birthDate:String, description:String, website:String) : Boolean{
        return (name.isNotBlank() && occupation.isNotBlank() && birthDate.isNotBlank() &&description.isNotBlank() && website.isNotBlank())
    }

}