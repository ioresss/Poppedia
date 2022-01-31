package tads.eaj.ufrn.poppedia.fragments.aboutus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import tads.eaj.ufrn.poppedia.R
import tads.eaj.ufrn.poppedia.fragments.dialogs.AboutUsDialog


class AboutUsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> {
                val dialog = AboutUsDialog()
                dialog.show(requireActivity().supportFragmentManager, "AboutUsDialog")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}