package com.example.jobapplication.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.jobapplication.R

class CustomAdapter (private val activity: Activity,
                     private val imagesArray: Array<Int>,
                     private val namesArray: Array<String>) : PagerAdapter(){
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = activity.layoutInflater
        //creating  xml file for custom viewpager
        val viewItem = inflater.inflate(R.layout.item, container, false)
        //finding id
        val imageView = viewItem.findViewById<ImageView>(R.id.imageView)
        val textView= viewItem.findViewById<TextView>(R.id.textview)
        imageView.setBackgroundResource(imagesArray[position])
        textView.setText(namesArray[position])
        container.addView(viewItem)
        return viewItem
    }
    override fun getCount(): Int {
        return imagesArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)
    }
}