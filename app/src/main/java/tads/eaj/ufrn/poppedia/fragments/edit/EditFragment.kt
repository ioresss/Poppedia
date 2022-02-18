package tads.eaj.ufrn.poppedia.fragments.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import tads.eaj.ufrn.poppedia.R
import tads.eaj.ufrn.poppedia.data.Celeb
import tads.eaj.ufrn.poppedia.databinding.FragmentEditBinding
import tads.eaj.ufrn.poppedia.fragments.details.DetailsFragmentArgs
import tads.eaj.ufrn.poppedia.fragments.dialogs.EditDialog
import java.text.SimpleDateFormat


class EditFragment : Fragment() {

    lateinit var editViewModel : EditViewModel
    lateinit var binding: FragmentEditBinding



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        setHasOptionsMenu(true)

        val args: EditFragmentArgs by navArgs()

        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        val viewModelFactory = EditViewModel.Factory(requireActivity().application, args.id)

        editViewModel = ViewModelProvider(this, viewModelFactory).get(EditViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel= editViewModel

        editViewModel.eventUpdateCeleb.observe(viewLifecycleOwner, {
            if(it){
                Navigation.findNavController(requireView()).navigate(EditFragmentDirections.actionEditFragmentToListFragment())
            }
        })

        /*editViewModel.findCeleb(args.id).observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtEditName).text = it.name
            view.findViewById<TextView>(R.id.txtEditWebsite).text = it.website
            view.findViewById<TextView>(R.id.txtEditOccupation).text = it.occupation
            view.findViewById<TextView>(R.id.txtEditDescription).text = it.description
            view.findViewById<TextView>(R.id.txtEditBirthDate).text = SimpleDateFormat("dd/MM/yyyy").format(it.birthDate)
        })

        view.findViewById<Button>(R.id.btnEdit).setOnClickListener{
            val name = view.findViewById<TextView>(R.id.txtEditName).text.toString()
            val birthDate = view.findViewById<TextView>(R.id.txtEditBirthDate).text.toString()
            val occupation = view.findViewById<TextView>(R.id.txtEditOccupation).text.toString()
            val description = view.findViewById<TextView>(R.id.txtEditDescription).text.toString()
            val website = view.findViewById<TextView>(R.id.txtEditWebsite).text.toString()
            if(validate(name, occupation, birthDate, description, website)){
                val celeb = Celeb(
                    args.id,
                    name,
                    SimpleDateFormat("dd/MM/yyyy").parse(birthDate),
                    occupation,
                    description,
                    website
                )
                editViewModel.updateCeleb(celeb)
                Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_editFragment_to_listFragment)
            }
            else{
                Toast.makeText(requireContext(), "Cannot update celebrity. Please, fill out all fields!", Toast.LENGTH_LONG).show()
            }

        }*/


        return binding.root
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> {
                val dialog = EditDialog()
                dialog.show(requireActivity().supportFragmentManager, "EditDialog")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validate(name:String, occupation: String, birthDate:String, description:String, website:String) : Boolean{
        return (name.isNotBlank() && occupation.isNotBlank() && birthDate.isNotBlank() &&description.isNotBlank() && website.isNotBlank())
    }

}