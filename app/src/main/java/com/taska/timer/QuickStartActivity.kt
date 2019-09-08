package com.taska.timer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.taska.timer.util.PrefUtil

import kotlinx.android.synthetic.main.activity_quick_start.*
import kotlinx.android.synthetic.main.content_quick_start.*

class QuickStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_start)
        setSupportActionBar(toolbar)
        initNumberPickers()
        supportActionBar?.title = "Quick Timer setup"
        fab.setOnClickListener { view ->
            val timerStartAct = Intent(this, TimerRunning::class.java)
            SimpleTimer.quickTimer.quickTimerExerciseList.clear()
            SimpleTimer.quickTimer.quickTimerExerciseList.add(QuickTimerExercise(1, numberPickerRndMinutes.value, numberPickerRndSeconds.value))
            SimpleTimer.quickTimer.rounds = numberPickerRounds.value
            //SimpleTimer.quickTimer.roundMin = numberPickerRndMinutes.value
            //SimpleTimer.quickTimer.roundSec = numberPickerRndSeconds.value
            SimpleTimer.quickTimer.restMin = numberPickerRestMinutes.value
            SimpleTimer.quickTimer.restSec = numberPickerRestSeconds.value
            SimpleTimer.quickTimer.isRestAfterRound = true
            SimpleTimer.isQuickTimer = true
            SimpleTimer.timerType = TimerType.Simple
            startActivity(timerStartAct)
        }
    }

    private fun initNumberPickers() {
        numberPickerRounds.minValue = 1
        numberPickerRounds.maxValue = 99
        numberPickerRndMinutes.minValue = 0
        numberPickerRndMinutes.maxValue = 60
        numberPickerRndSeconds.minValue = 0
        numberPickerRndSeconds.maxValue = 59
        numberPickerRestMinutes.minValue = 0
        numberPickerRestMinutes.maxValue = 60
        numberPickerRestSeconds.minValue = 0
        numberPickerRestSeconds.maxValue = 59

        numberPickerRounds.value = PrefUtil.getPreviousSimpleTimerSetRounds(this)
        numberPickerRndMinutes.value = PrefUtil.getPreviousSimpleTimerSetMinutes(this)
        numberPickerRndSeconds.value = PrefUtil.getPreviousSimpleTimerSetSeconds(this)
        numberPickerRestMinutes.value = PrefUtil.getPreviousSimpleTimerSetRestMinutes(this)
        numberPickerRestSeconds.value = PrefUtil.getPreviousSimpleTimerSetRestSeconds(this)
    }



}
