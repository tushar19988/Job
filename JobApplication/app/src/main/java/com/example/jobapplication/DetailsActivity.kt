package com.example.jobapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.jobapplication.fragment.HomeFragment

class DetailsActivity : AppCompatActivity() {

    lateinit var txtcname: TextView
    lateinit var txtpost: TextView
    lateinit var txteducation: TextView
    lateinit var txtlocation: TextView
    lateinit var txtlastdate: TextView
    lateinit var txtdetails: TextView
    lateinit var txtsummary: TextView
    lateinit var txtabout: TextView
    lateinit var btnShare: ImageButton
    lateinit var btnback: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        txtcname=findViewById(R.id.txtcname)
        txtpost=findViewById(R.id.txtpost)
        txteducation=findViewById(R.id.txteducation)
        txtlocation=findViewById(R.id.txtlocation)
        txtlastdate=findViewById(R.id.txtlastdate)
        txtdetails=findViewById(R.id.txtdetail)
        txtsummary=findViewById(R.id.txtsummary)
        txtabout=findViewById(R.id.txtabout)
        btnShare=findViewById(R.id.btnShare)
        btnback=findViewById(R.id.btnback)

        initView()
    }
    private fun initView() {
        txtcname.text = intent.getStringExtra("COMPANY_NAME")
        txtpost.text = intent.getStringExtra("POST")
        txteducation.text = intent.getStringExtra("EDUCATION")
        txtlocation.text = intent.getStringExtra("LOCATION")
        txtlastdate.text = intent.getStringExtra("LASTDATE")
        txtdetails.text = intent.getStringExtra("JOB_DETAILS")
        txtsummary.text = intent.getStringExtra("JOB_SUMMARY")
        txtabout.text = intent.getStringExtra("ABOUT")



        btnShare.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.lbl_subject_here)

                sharingIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Company Name:"+intent.getStringExtra("COMPANY_NAME") +"\n"+ " Post: " + intent.getStringExtra("POST")+"\n"
                            + " Education:" + intent.getStringExtra("EDUCATION") +"\n"+ "Location: " + intent.getStringExtra("LOCATION") +"\n"+
                            " " + intent.getStringExtra("LASTDATE")
                            )

                startActivity(
                    Intent.createChooser(
                        sharingIntent,
                        getString(R.string.lbl_share_text_via)
                    )
                )
            }
        })

        btnback.setOnClickListener {

            val i = Intent(this@DetailsActivity, NavigationActivity::class.java)
            startActivity(i)
            finish()

        }
    }
}