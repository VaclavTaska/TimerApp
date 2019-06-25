package com.taska.timer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;


import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.ic_timer_white_24dp)
        supportActionBar?.title = "    Timer App"


        buttonQuickStart.setOnClickListener{
            val quickTimerAct = Intent(this, QuickStartActivity::class.java)
            startActivity(quickTimerAct)
        }


    }



}
