package com.taska.timer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.taska.timer.util.PrefUtil


import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.ic_timer_white_24dp)
        supportActionBar?.title = "    Timer App"
        buttonAdvancedOne.text = PrefUtil.getAdvancedExerciseOneName(this)
        buttonAdvancedTwo.text = PrefUtil.getAdvancedExerciseTwoName(this)


        buttonQuickStart.setOnClickListener{
            val quickTimerAct = Intent(this, QuickStartActivity::class.java)
            startActivity(quickTimerAct)
        }

        buttonAdvancedOne.setOnClickListener {
            val advancedTimerAct = Intent(this, AdvancedOneActivity::class.java)
            startActivity(advancedTimerAct)
        }

        buttonAdvancedTwo.setOnClickListener{
            val advancedTimerAct = Intent(this, AdvancedTwoActivity::class.java)
            startActivity(advancedTimerAct)
        }

        buttonInfo.setOnClickListener{
            val infoTimerAct = Intent(this, InformationActivity::class.java)
            startActivity(infoTimerAct)
        }

        fun onResume(){
            super.onResume()
            buttonAdvancedOne.text = PrefUtil.getAdvancedExerciseOneName(this)
            buttonAdvancedTwo.text = PrefUtil.getAdvancedExerciseTwoName(this)
        }
    }


}
