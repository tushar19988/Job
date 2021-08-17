package com.example.jobapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobapplication.R
import com.example.jobapplication.RetriveData

class DataAdapter(private val userlist :ArrayList<RetriveData>):RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=LayoutInflater.from(parent.context).inflate( R.layout.item_gujarat_adapter,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentitem= userlist[position]
        holder.companyname.text=currentitem.companyname
        holder.post.text=currentitem.post
        holder.education.text=currentitem.education
        holder.location.text=currentitem.location
        holder.lastdate.text=currentitem.lastdate
    }

    override fun getItemCount(): Int {
      return userlist.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val companyname:TextView=itemView.findViewById(R.id.txtcname)
        val post:TextView=itemView.findViewById(R.id.txtpost)
        val education:TextView=itemView.findViewById(R.id.txteducation)
        val location:TextView=itemView.findViewById(R.id.txtlocation)
        val lastdate:TextView=itemView.findViewById(R.id.txtlastdate)

    }

}