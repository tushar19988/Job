package com.example.jobapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jobapplication.authentication.SigninActivity
import com.example.jobapplication.fragment.HomeFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class ForgotpassActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    lateinit var btncnt:Button
    lateinit var edtespmail:EditText
    lateinit var btnback:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)

        mAuth = FirebaseAuth.getInstance()
        btncnt=findViewById(R.id.btncnt)
        edtespmail=findViewById(R.id.edtespmail)
        btnback=findViewById(R.id.btnback)

        initView()
    }

    private fun initView() {

        btncnt.setOnClickListener (object : View.OnClickListener {
            override fun onClick(v: View?) {

                val email: String = edtespmail.getText().toString().trim()

                edtespmail.text.clear()
                if (TextUtils.isEmpty(email)) {
                    validation()
                    return
                }

                mAuth?.sendPasswordResetEmail(email)
                    ?.addOnCompleteListener(OnCompleteListener<Void?> { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@ForgotpassActivity,
                                R.string.lbl_we_have_sent_you_instructions_to_reset_your_password,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@ForgotpassActivity,
                                R.string.lbl_failed_to_send_reset_email,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })
            }
        })
        btnback.setOnClickListener {

            val i = Intent(this@ForgotpassActivity, SigninActivity::class.java)
            startActivity(i)
            finish()

        }
    }
    private fun validation() {
        if (edtespmail.text.toString().trim().isEmpty()) {
            edtespmail.error = "Enter your registered email id"
        }
        else{
            val intent= Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
    }


}
