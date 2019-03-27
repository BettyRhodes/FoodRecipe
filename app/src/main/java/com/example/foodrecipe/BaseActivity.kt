package com.example.foodrecipe

import android.annotation.SuppressLint
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout

abstract class BaseActivity : AppCompatActivity(){

    @SuppressLint("InflateParams")
    override fun setContentView(layoutResID: Int) {
        val constraintLayout: ConstraintLayout = layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout
        val frameLayout = constraintLayout.findViewById<FrameLayout>(R.id.activity_content)

        layoutInflater.inflate(layoutResID, frameLayout, true)
        super.setContentView(constraintLayout)
    }
}