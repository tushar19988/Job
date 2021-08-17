package com.example.jobapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val url: TextView =itemView.findViewById(R.id.txtemp)
}