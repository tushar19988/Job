package com.example.jobapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.jobapplication.fragment.HomeFragment

class ResultdetailActivity : AppCompatActivity() {

    lateinit var txtemp: TextView
    lateinit var txtexam: TextView
    lateinit var txtuni: TextView
    lateinit var txtlocation: TextView
    lateinit var btnrview: Button
    lateinit var btnback: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultdetail) 

        txtemp=findViewById(R.id.txtemp)
        txtexam=findViewById(R.id.txtexam)
        txtuni=findViewById(R.id.txtuni)
        txtlocation=findViewById(R.id.txtlocation)
        btnrview=findViewById(R.id.btnrview)
        btnback=findViewById(R.id.btnback)

        initView()
    }

    private fun initView() {

            btnrview.setOnClickListener {
                val uri: Uri = Uri.parse(intent.getStringExtra("LINK"))

                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

            txtemp.text=intent.getStringExtra("URL")
            txtexam.text=intent.getStringExtra("EXAM")
            txtuni.text=intent.getStringExtra("UNIVERSITY")
            txtlocation.text=intent.getStringExtra("LOCATION")

        btnback.setOnClickListener {
            finish()

        }
    }
}