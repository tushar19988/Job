package com.example.jobapplication.authentication

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.NonNull
import com.example.androidtraining.helper.IS_LOGIN
import com.example.androidtraining.helper.PreferenceHelper.set
import com.example.jobapplication.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class SigninActivity : BaseActivity(),View.OnClickListener {

    lateinit var ref: DatabaseReference
    private var mAuth: FirebaseAuth? = null
    lateinit var txtsignup: TextView
    lateinit var btnsignin: Button
    lateinit var txtskip: TextView
    lateinit var edtemail: EditText
    lateinit var edtpass: EditText
    lateinit var txtforgetpass: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        txtsignup = findViewById(R.id.txtsignup)
        txtskip = findViewById(R.id.txtskip)
        btnsignin = findViewById(R.id.btnsignin)
        edtemail = findViewById(R.id.edtemail)
        edtpass = findViewById(R.id.edtpass)
        txtforgetpass = findViewById(R.id.txtforgetpass)


        mAuth = FirebaseAuth.getInstance()
        initView()

    }

    private fun initView() {

        txtskip.setOnClickListener(this)
        txtforgetpass.setOnClickListener(this)
        txtsignup.setOnClickListener(this)

        btnsignin.setOnClickListener {

            checkVlidation()
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txtskip -> {
                val intent = Intent(this@SigninActivity, NavigationActivity::class.java)
                startActivity(intent)
            }
            R.id.txtforgetpass -> {
                val intent = Intent(this@SigninActivity, ForgotpassActivity::class.java)
                startActivity(intent)
            }
            R.id.txtsignup -> {
                val intent = Intent(this@SigninActivity, SignupActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun checkVlidation() {
        if (edtemail.text.toString().trim().isEmpty()) {
            edtemail.error = getString(R.string.lbl_enter_your_registered_email_id)

        }  else if (edtpass.text.toString().trim().isEmpty()) {
            edtpass.error = getString(R.string.lbl_please_password)

        }  else {
            loginData()

        }
    }

    private fun loginData() {
        mAuth?.signInWithEmailAndPassword(
            edtemail.text.toString().trim(),
            edtpass.text.toString().trim()
        )
            ?.addOnCompleteListener(this@SigninActivity, object :
                OnCompleteListener<AuthResult> {
                override fun onComplete(@NonNull task: Task<AuthResult>) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", getString(R.string.lbl_signInWithEmail_success))


                        val intent =
                            Intent(this@SigninActivity, NavigationActivity::class.java)
                        prif[IS_LOGIN] = true

                        onStart()
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(
                            "TAG",
                            getString(R.string.lbl_signInWithEmail_failure),
                            task.getException()
                        )
                        Toast.makeText(
                            this@SigninActivity,
                            R.string.lbl_authentication_failed,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

    }

}







