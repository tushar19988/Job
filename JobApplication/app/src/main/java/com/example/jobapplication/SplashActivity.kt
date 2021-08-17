package com.example.jobapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.androidtraining.helper.IS_LOGIN
import com.example.androidtraining.helper.PreferenceHelper.get

class SplashActivity : BaseActivity() {

    private val SPALSH_SCREEN=3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initView()
    }

    private fun initView() {
        Handler().postDelayed({

            if (prif[IS_LOGIN]!!){
                val intent= Intent(this,NavigationActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent= Intent(this,IntroductionActivity::class.java)
                startActivity(intent)
                finish()
            }
        },SPALSH_SCREEN.toLong())
    }
}













