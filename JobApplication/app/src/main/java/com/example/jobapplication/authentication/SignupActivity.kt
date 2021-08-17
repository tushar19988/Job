package com.example.jobapplication.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.example.jobapplication.AddData
import com.example.jobapplication.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class SignupActivity : AppCompatActivity() {

    lateinit var txtsignin: TextView
    lateinit var btnsignup: Button
    lateinit var edtemaxilu: EditText
    lateinit var edtmnumber: EditText
    lateinit var edtpassu: EditText
    lateinit var edtconfirmpass: EditText
    private var mAuth: FirebaseAuth? = null
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    lateinit var btnback: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()
        txtsignin = findViewById(R.id.txtsignin)
        edtemaxilu = findViewById<EditText>(R.id.edtemailu)
        edtmnumber = findViewById<EditText>(R.id.edtmnumber)
        edtpassu = findViewById<EditText>(R.id.edtpassu)
        edtconfirmpass = findViewById<EditText>(R.id.edtconfirmpass)
        btnsignup = findViewById(R.id.btnsignup)
        btnback = findViewById(R.id.btnback)

        intiView()

    }

    private fun intiView() {


        txtsignin.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SigninActivity::class.java
                )
            )
        }
        btnsignup.setOnClickListener {

            checkVlidation()


        }





        btnback.setOnClickListener {

            val i = Intent(this@SignupActivity, SigninActivity::class.java)
            startActivity(i)
            finish()

        }


    }
    private fun checkVlidation() {
        if (edtemaxilu.text.toString().trim().isEmpty()) {
            edtemaxilu.error = getString(R.string.lbl_please_enter_your_email_address)

        } else if (!edtemaxilu.text.toString().matches(emailPattern.toRegex())) {
            edtemaxilu.error = getString(R.string.lbl_invalid_email)

        } else if (edtmnumber.text.toString().trim().isEmpty()) {
            edtmnumber.error =getString(R.string.lbl_enter_phone_number)

        } else if (edtpassu.text.toString().trim().isEmpty()) {
            edtpassu.error = getString(R.string.lbl_please_password)

        } else if (edtconfirmpass.text.toString().trim().isEmpty()) {
            edtconfirmpass.error = getString(R.string.lbl_enter_confirm_password)

        } else if (!edtpassu.text.toString().contentEquals(edtconfirmpass.text.toString())) {
            edtconfirmpass.error = getString(R.string.lbl_enter_correct_password)

        } else {
            loginData()

        }
    }
    fun loginData() {
        val ref = FirebaseDatabase.getInstance().getReference("user")
        val userid = ref.push().key



        val user = AddData(
            email = edtemaxilu.text.toString(),
            mobilenumber = edtmnumber.text.toString(),
            password = edtpassu.text.toString(),
            confirmpassword = edtconfirmpass.text.toString()
        )
        ref.child(userid!!).setValue(user).addOnCompleteListener {
            Toast.makeText(
                this@SignupActivity,
                R.string.lbl_sent_successfully,
                Toast.LENGTH_SHORT
            ).show()
            startActivity(
                Intent(
                    this@SignupActivity,
                    SigninActivity::class.java
                )
            )
        }
        mAuth?.createUserWithEmailAndPassword(
            edtemaxilu.text.toString().trim(),
            edtpassu.text.toString().trim()
        )
            ?.addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                override fun onComplete(@NonNull task: Task<AuthResult>) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", getString(R.string.lbl_createUserWithEmail_success))
                        startActivity(
                            Intent(
                                this@SignupActivity,
                                SigninActivity::class.java
                            )
                        )
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d(
                            "TAG",
                            getString(R.string.lbl_createUserWithEmail_failure),
                            task.getException()
                        )
                        Toast.makeText(
                            this@SignupActivity, R.string.lbl_authentication_failed,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    // ...
                }
            })
    }
}















