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


class MpFragment : Fragment() {

    var ref: DatabaseReference?=null
    lateinit var userRecyclerView: RecyclerView
    var progressDialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_mp, container, false)
        userRecyclerView=view.findViewById(R.id.userlistmadhya)

        ref = FirebaseDatabase.getInstance().getReference("MadhyaPradesh")
        progressDialog = ProgressDialog(context)
        progressDialog!!.setCancelable(false)

        initView(view)
        return view

    }
    private fun initView(view: View?) {
        userRecyclerView.layoutManager= LinearLayoutManager(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.show()
        val firebaseAdapter= object : FirebaseRecyclerAdapter<RetriveData, ViewHolder>(


            RetriveData::class.java,
            R.layout.item_gujarat_adapter,
            ViewHolder::class.java,
            ref
        ){
            override fun populateViewHolder(p0: ViewHolder?, p1: RetriveData?, p2: Int) {
                p0?.itemView?.findViewById<TextView>(R.id.txtcname)?.setText(p1?.companyname)
                p0?.itemView?.findViewById<TextView>(R.id.txtpost)?.setText(p1?.post)
                p0?.itemView?.findViewById<TextView>(R.id.txteducation)?.setText(p1?.education)
                p0?.itemView?.findViewById<TextView>(R.id.txtlocation)?.setText(p1?.location)
                p0?.itemView?.findViewById<TextView>(R.id.txtlastdate)?.setText(p1?.lastdate)
                p0?.itemView?.findViewById<TextView>(R.id.txtdetail)?.setText(p1?.jobdetails)
                p0?.itemView?.findViewById<TextView>(R.id.txtsummary)?.setText(p1?.jobsummary)
                p0?.itemView?.findViewById<TextView>(R.id.txtabout)?.setText(p1?.about)

                progressDialog?.dismiss()
                p0?.itemView?.findViewById<Button>(R.id.btnview)?.setOnClickListener {

                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra("COMPANY_NAME", p1?.companyname)
                    intent.putExtra("POST", p1?.post)
                    intent.putExtra("EDUCATION", p1?.education)
                    intent.putExtra("LOCATION", p1?.location)
                    intent.putExtra("LASTDATE", p1?.lastdate)
                    intent.putExtra("JOB_DETAILS", p1?.jobdetails)
                    intent.putExtra("JOB_SUMMARY", p1?.jobsummary)
                    intent.putExtra("ABOUT", p1?.about)
                    startActivity(intent)
                }
            }

        }

        userRecyclerView.adapter=firebaseAdapter

    }

}