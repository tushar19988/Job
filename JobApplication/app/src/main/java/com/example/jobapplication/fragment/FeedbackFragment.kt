package com.example.jobapplication.fragment

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jobapplication.AddFeedback
import com.example.jobapplication.R
import com.google.firebase.database.FirebaseDatabase


class FeedbackFragment : Fragment() {

    lateinit var btnsubmit:Button
    lateinit var edtemailf:EditText
    lateinit var edtnamef:EditText
    lateinit var edtmsg:EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_feedback, container, false)

        btnsubmit=view.findViewById(R.id.btnsubmit)
        edtemailf=view.findViewById(R.id.edtemailf)
        edtnamef=view.findViewById(R.id.edtnamef)
        edtmsg=view.findViewById(R.id.edtmsg)

        initView(view)
        return view

    }

    private fun initView(view: View?) {
        btnsubmit.setOnClickListener {

            if (isConnected && notEmpty())
             {
                val ref= FirebaseDatabase.getInstance().getReference("Feedback")
                val userid = ref.push().key
                val user = AddFeedback(name = edtnamef.text.toString(),email = edtemailf.text.toString(),message = edtmsg.text.toString() )
                ref.child(userid!!).setValue(user).addOnCompleteListener {

                    Toast.makeText( activity, R.string.lbl_sent_successfully, Toast.LENGTH_SHORT).show()

                    edtemailf.text.clear()
                    edtnamef.text.clear()
                    edtmsg.text.clear()
                }
            } else if (!isConnected){
                Toast.makeText(context, R.string.lbl_no_internet_connection_please_connect_internet, Toast.LENGTH_SHORT)
                    .show()

            }
            else{
                edtnamef.error=getString(R.string.lbl_enter_ur_name)
                edtemailf.error=getString(R.string.lbl_please_enter_your_email_address)
                edtmsg.error=getString(R.string.lbl_enter_feed)
            }
        }
    }

    val isConnected: Boolean
        get() {
            var connected = false
            try {
                val cm = context?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
                val nInfo = cm.activeNetworkInfo
                connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
                return connected
            } catch (e: Exception) {
                e.message?.let { Log.e(getString(R.string.lbl_connectivity_exception), it) }
            }
            return connected
        }

    private fun notEmpty(): Boolean =
        edtemailf.text.toString().isNotEmpty() && edtnamef.text.toString().isNotEmpty() && edtmsg.text.toString().isNotEmpty()

}


