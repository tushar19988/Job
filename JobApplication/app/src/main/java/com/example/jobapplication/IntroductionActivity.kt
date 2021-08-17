package com.example.jobapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.jobapplication.adapter.CustomAdapter
import com.example.jobapplication.authentication.SigninActivity
import java.util.*

class IntroductionActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var txtskip: TextView

    var imageId =
        arrayOf(R.drawable.backone, R.drawable.backtwo, R.drawable.backthree,R.drawable.backfour,R.drawable.backfive)
    var imagesName = arrayOf("Choose a job you love, and you will never have to work a day in your life.", "It's time to start living the life we've imagined.", "In the middle of difficulty lies opportunity.","Only those who dare to fail greatly can ever achieve greatly.","It is never too late to be what you might have been.")
    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 3000 // time in milliseconds between successive task executions.
    private var dotscount = 0
    private lateinit var dots: Array<ImageView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)


        viewPager = findViewById(R.id.viewpager)
        txtskip=findViewById(R.id.txtskip)

        initView()


    }

    private fun initView() {
        txtskip.setOnClickListener (View.OnClickListener { // Check for Internet Connection
                if (isConnected) {

                    startActivity(
                        Intent(this,
                            SigninActivity::class.java)
                    )
                } else {
                    Toast.makeText(applicationContext, getString(R.string.lbl_no_internet_connection_please_connect_internet), Toast.LENGTH_SHORT)
                        .show()
                }
            })




        val adapter: PagerAdapter = CustomAdapter(
            this@IntroductionActivity,
            imageId,
            imagesName
        )
        viewPager.adapter = adapter
        dotscount = adapter.getCount()
        dots = arrayOfNulls(dotscount)
        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.non_active_dot
                )
            )
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)

        }
        dots[0]!!.setImageDrawable(
            ContextCompat.getDrawable(
                getApplicationContext(),
                R.drawable.active_dot
            )
        )
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!
                        .setImageDrawable(
                            ContextCompat.getDrawable(
                                getApplicationContext(),
                                R.drawable.non_active_dot
                            )
                        )
                }
                dots[position]!!
                    .setImageDrawable(
                        ContextCompat.getDrawable(
                            getApplicationContext(),
                            R.drawable.active_dot
                        )
                    )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        /*After setting the adapter use the timer */
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == 5) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }
        timer = Timer() // This will create a new Thread
        timer!!.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)

    }
    val isConnected: Boolean
        get() {
            var connected = false
            try {
                val cm =
                    applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                val nInfo = cm.activeNetworkInfo
                connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
                return connected
            } catch (e: Exception) {
                e.message?.let { Log.e(getString(R.string.lbl_connectivity_exception), it) }
            }
            return connected
        }

}


