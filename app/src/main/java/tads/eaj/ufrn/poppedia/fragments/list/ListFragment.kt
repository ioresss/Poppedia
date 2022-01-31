package tads.eaj.ufrn.poppedia.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tads.eaj.ufrn.poppedia.R
import tads.eaj.ufrn.poppedia.fragments.dialogs.ListDialog

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.listRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        listViewModel.readAllCelebs.observe(viewLifecycleOwner, Observer { celeb ->
            adapter.setData(celeb)
        })

        view.findViewById<FloatingActionButton>(R.id.floatingBtnAdd).setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_insertFragment)
        }

        recyclerView.addOnItemTouchListener(ListTouchListener(requireContext(), recyclerView,
            object : ListTouchListener.OnItemClickListener{
                override fun onItemClick(v: View, position: Int) {
                    val action = ListFragmentDirections.actionListFragmentToDetailsFragment(adapter.celebList[position].id)
                    findNavController().navigate(action)
                }

                override fun onItemLongClick(v: View, position: Int) {
                    val action = ListFragmentDirections.actionListFragmentToEditFragment(adapter.celebList[position].id)
                    findNavController().navigate(action)
                }
            }),)
        view.findViewById<Button>(R.id.btnDeleteAll).setOnClickListener{
            listViewModel.deleteAllCelebs()
        }
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> {
                val dialog = ListDialog()
                dialog.show(requireActivity().supportFragmentManager, "ListDialog")
            }
        }
        return super.onOptionsItemSelected(item)
    }

}