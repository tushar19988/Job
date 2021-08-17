package com.example.jobapplication

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.androidtraining.helper.IS_LOGIN
import com.example.jobapplication.authentication.SigninActivity
import com.example.jobapplication.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.example.androidtraining.helper.PreferenceHelper.get



class NavigationActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var  drawerLayout:DrawerLayout
    private var mAuth: FirebaseAuth? = null
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val nav_view: BottomNavigationView = findViewById(R.id.navb_view)


        val toggle = ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toolbar.setNavigationIcon(R.drawable.navone);
        drawerLayout.addDrawerListener(toggle)

        navView.setNavigationItemSelectedListener(this)
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val fragment= HomeFragment()
        replaceFragment(fragment)

    }

    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.framLayout,fragment).commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home ->{
                val fragment= HomeFragment()
                toolbar.setTitle(getString(R.string.lbl_guj))
                replaceFragment(fragment)

            }
            R.id.nav_results ->{
                val fragment= ResultsFragment()
                toolbar.setTitle(getString(R.string.lbl_sarka_res))
                replaceFragment(fragment)

            }
            R.id.nav_employee ->{
                val fragment= EmployeeFragment()
                toolbar.setTitle(getString(R.string.lbl_emp_news))
                replaceFragment(fragment)

            }
            R.id.nav_feedback ->{
                val fragment= FeedbackFragment()
                toolbar.setTitle(getString(R.string.lbl_feed))
                replaceFragment(fragment)

            }
            R.id.nav_aboutus ->{
                val fragment= AboutusFragment()
                toolbar.setTitle(getString(R.string.lbl_about_us))
                replaceFragment(fragment)

            }
            R.id.nav_logout->{


               logout()
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logout() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)

        builder.setTitle(getString(R.string.lbl_con))
        builder.setMessage(getString(R.string.lbl_you_want_to_logout))

        builder.setPositiveButton(
            getString(R.string.lbl_yes),
            DialogInterface.OnClickListener { dialog, which ->

                if (prif[IS_LOGIN]!!){
                    mAuth?.signOut()
                    finish()
                    val intent = Intent(this@NavigationActivity, SigninActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }else{
                    Toast.makeText(this, getString(R.string.lbl_you_r_not_logged_in_what_to_login), Toast.LENGTH_SHORT).show()
                }



            })

        builder.setNegativeButton(
            getString(R.string.lbl_no),

            DialogInterface.OnClickListener { dialog, which -> // Do nothing
                dialog.dismiss()
            })

        val alert: android.app.AlertDialog? = builder.create()
        alert?.show()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            R.id.navb_guj ->{
                val fragment=HomeFragment()
                toolbar.setTitle(getString(R.string.lbl_guj))
                replaceFragment(fragment)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navb_rajsthan->{
                val fragment=RajasthanFragment()
                toolbar.setTitle(getString(R.string.lbl_raj))
                replaceFragment(fragment)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navb_mp ->{
                val fragment=MpFragment()
                toolbar.setTitle(getString(R.string.lbl_mp))
                replaceFragment(fragment)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navb_maharastra ->{
                val fragment=MaharastraFragment()
                toolbar.setTitle(getString(R.string.lbl_maharastra))
                replaceFragment(fragment)
                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }
}