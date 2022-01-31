package tads.eaj.ufrn.poppedia.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tads.eaj.ufrn.poppedia.fragments.details.DetailsFragmentArgs
import tads.eaj.ufrn.poppedia.R
import tads.eaj.ufrn.poppedia.fragments.dialogs.DetailsDialog
import tads.eaj.ufrn.poppedia.fragments.list.ListFragmentDirections
import tads.eaj.ufrn.poppedia.fragments.list.ListViewModel
import java.text.SimpleDateFormat
import java.util.*


class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_details, container, false)
        val args:DetailsFragmentArgs by navArgs()
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
       detailsViewModel.findCeleb(args.id).observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtDetailName).text = it.name
            view.findViewById<TextView>(R.id.txtDetailWebsite).text = it.website
            view.findViewById<TextView>(R.id.txtDetailBirthDate).text = SimpleDateFormat("MMMM dd, yyyy", Locale.US).format(it.birthDate)
            view.findViewById<TextView>(R.id.txtDetailOccupation).text = it.occupation
            view.findViewById<TextView>(R.id.txtDetailDescription).text = it.description
            val celeb = it
           view.findViewById<FloatingActionButton>(R.id.floatingBtnDelete).setOnClickListener{
               detailsViewModel.deleteCeleb(celeb)
               val action = DetailsFragmentDirections.actionDetailsFragmentToListFragment()
               view.findNavController().navigate(action)
           }
        })
        view.findViewById<FloatingActionButton>(R.id.floatingBtnEdit).setOnClickListener{
            val action = DetailsFragmentDirections.actionDetailsFragmentToEditFragment(args.id)
            view.findNavController().navigate(action)
        }



        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> {
                val dialog = DetailsDialog()
                dialog.show(requireActivity().supportFragmentManager, "DetailsDialog")
            }
        }
        return super.onOptionsItemSelected(item)
    }

}