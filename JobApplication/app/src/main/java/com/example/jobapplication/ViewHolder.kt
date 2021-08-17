package com.example.jobapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val companyname: TextView =itemView.findViewById(R.id.txtcname)
    val post: TextView =itemView.findViewById(R.id.txtpost)
    val education: TextView =itemView.findViewById(R.id.txteducation)
    val location: TextView =itemView.findViewById(R.id.txtlocation)
    val lastdate: TextView =itemView.findViewById(R.id.txtlastdate)
}