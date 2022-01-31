package tads.eaj.ufrn.poppedia.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import tads.eaj.ufrn.poppedia.R
import tads.eaj.ufrn.poppedia.data.Celeb
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var celebList = emptyList<Celeb>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = celebList[position]
        holder.itemView.findViewById<TextView>(R.id.txtRowId).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.txtRowOccupation).text = currentItem.occupation
        holder.itemView.findViewById<TextView>(R.id.txtRowName).text = currentItem.name
        holder.itemView.findViewById<TextView>(R.id.txtRowBirthdate).text = SimpleDateFormat("MMMM dd, yyyy", Locale.US).format(currentItem.birthDate)
    }

    override fun getItemCount(): Int {
        return celebList.size
    }

    fun setData(celebs: List<Celeb>){
        this.celebList=celebs
        notifyDataSetChanged()
    }


}