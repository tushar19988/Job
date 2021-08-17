package com.example.jobapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidtraining.helper.PreferenceHelper

open class BaseActivity : AppCompatActivity() {
    lateinit var  prif :SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prif=PreferenceHelper.customPrefs(this)
    }

}