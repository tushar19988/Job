package com.example.jobapplication.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobapplication.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ResultsFragment : Fragment() {

    var ref: DatabaseReference?=null
    lateinit var userRecyclerView: RecyclerView
    var progressDialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_results, container, false)

        userRecyclerView=view.findViewById(R.id.userliste)

        ref = FirebaseDatabase.getInstance().getReference("Employee")
        progressDialog = ProgressDialog(context)
        progressDialog!!.setCancelable(false)

        initView(view)
        return view
    }

    private fun initView(view: View?) {
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.show()
        val firebaseAdapter = object : FirebaseRecyclerAdapter<RetriveDatae, EViewHolder>(


            RetriveDatae::class.java,
            R.layout.item_emplyoee_adapter,
            EViewHolder::class.java,
            ref
        ) {
            override fun populateViewHolder(p0: EViewHolder?, p1: RetriveDatae?, p2: Int) {
                p0?.itemView?.findViewById<TextView>(R.id.txtemp)?.setText(p1?.url)
                p0?.itemView?.findViewById<TextView>(R.id.txtexam)?.setText(p1?.exam)
                p0?.itemView?.findViewById<TextView>(R.id.txtuni)?.setText(p1?.university)
                p0?.itemView?.findViewById<TextView>(R.id.txtlocation)?.setText(p1?.location)
                p0?.itemView?.findViewById<Button>(R.id.btnrview)?.setText(p1?.link)

                progressDialog?.dismiss()
                p0?.itemView?.findViewById<Button>(R.id.btnview)?.setOnClickListener {

                    val intent = Intent(context, ResultdetailActivity::class.java)
                    intent.putExtra("URL",p1?.url)
                    intent.putExtra("EXAM",p1?.exam)
                    intent.putExtra("UNIVERSITY",p1?.university)
                    intent.putExtra("LOCATION",p1?.location)
                    intent.putExtra("LINK",p1?.link)
                    startActivity(intent)
                }

            }
        }
        userRecyclerView.adapter=firebaseAdapter
    }


}


