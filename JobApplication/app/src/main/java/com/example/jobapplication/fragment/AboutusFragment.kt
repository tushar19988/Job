package com.example.jobapplication.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.jobapplication.R


class AboutusFragment : Fragment() {

    lateinit var edtMobileNumber: TextView
    lateinit var btncalling:Button
    lateinit var btnemail:Button
    private val requestCall=1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_aboutus, container, false)

        initView(view)
        return view
    }

    private fun initView(view: View?) {

        val btnCalling=view?.findViewById<Button>(R.id.btncalling)
        edtMobileNumber=view?.findViewById<TextView>(R.id.txtnum)!!

        btnemail=view.findViewById(R.id.btnemail)

        btnCalling?.setOnClickListener {
            calling()
        }

        btnemail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val recipients = arrayOf("info@waytocode.in")
            intent.putExtra(Intent.EXTRA_EMAIL, recipients)
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.lbl_subject_text_here___))
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.lbl_body_of_the_content_here___))
            intent.putExtra(Intent.EXTRA_CC, getString(R.string.lbl_infowaytocode_in))
            intent.type = "text/html"
            intent.setPackage("com.google.android.gm")
            startActivity(Intent.createChooser(intent, getString(R.string.lbl_send_mail)) )
        }

    }
    private fun calling() {
        val number=edtMobileNumber.text.toString().trim()
        if (number.isNotEmpty()){
            if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE),requestCall)

            }else{
                val dial="tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }

        }else{
            Toast.makeText(context, R.string.lbl_enter_phone_number, Toast.LENGTH_SHORT).show()

        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode==requestCall){
            if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                calling()
            }else{
                Toast.makeText(context, R.string.lbl_permission_denied, Toast.LENGTH_SHORT).show()
            }
        }
    }


        }

