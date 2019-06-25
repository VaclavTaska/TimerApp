package com.taska.timer

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_timer_running.*
import kotlinx.android.synthetic.main.content_main2.*
import kotlinx.android.synthetic.main.content_timer_running.*
import kotlin.concurrent.thread

class TimerRunning : AppCompatActivity() {

    var timeLeft : Long = 0
    var rounds = SimpleTimer.quickTimer.rounds!!
    var totalRounds = rounds * 2
    var counterTime : CountDownTimer? = null
    val f : NumberFormat = DecimalFormat("00")
    val timeCountDown : Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_running)
        setSupportActionBar(toolbar)
        updateRoundText()
        updateTextOverview("Let's work!")
        playStartSound(R.raw.gamestartcountdown)
        initialPause()
    }


    fun startTimer(time : Long) {
        playStartSound(R.raw.belldingloud)
        counterTime = object : CountDownTimer(time, timeCountDown) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                val minutes : Int = ((timeLeft / timeCountDown) / 60).toInt()
                val seconds : Int = ((timeLeft / timeCountDown) % 60).toInt()
                textViewTime.setText(f.format(minutes) + ":" + f.format(seconds))
                if(timeLeft <= 4000) {
                    playStartSound(R.raw.ticksound)
                }
            }

            override fun onFinish() {
                totalRounds -= 1
                if (totalRounds <= 1) {
                    //textViewTime.setText("Time ended")
                    updateTextOverview("Time ended")
                    playSound()
                    rounds--
                    updateRoundText()
                }
                else if (totalRounds %2 == 0) {
                    rounds--
                    updateRoundText()
                    updateTextOverview("Let's work!")
                    startTimer(getQuickTime())
                } else {
                    startTimer(getQuickRestTime())
                    updateTextOverview("Rest now")
                }

            }
        }.start()
        //counterTime.start()
    }

    override fun onPause() {
        super.onPause()
        counterTime?.cancel()
    }

    fun saveObjectState() {
        val timeObj = QuickTimerData()
        timeObj.rounds = rounds

    }

    private fun initialPause() {
        val timeToWait : Long = 3200
        textViewTime.setText("Get Ready!")
        val counterTime = object : CountDownTimer(timeToWait, timeCountDown) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                startTimer(getQuickTime())
            }
        }
        counterTime.start()
    }

    private fun getQuickTime() : Long {
        var roundMinute : Long = (SimpleTimer.quickTimer.roundMin)!!.toLong()
        var roundSecond : Long = (SimpleTimer.quickTimer.roundSec)!!.toLong()
        return ((roundMinute * 60000) + (roundSecond * 1000))
    }

    private fun getQuickRestTime() : Long {
        var roundMinute : Long = (SimpleTimer.quickTimer.restMin)!!.toLong()
        var roundSecond : Long = (SimpleTimer.quickTimer.restSec)!!.toLong()
        return ((roundMinute * 60000) + (roundSecond * 1000))
    }

    private fun updateRoundText() {
        textViewRounds.text = rounds.toString()
    }

    private fun playSound() {
        val notification : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r : Ringtone = RingtoneManager.getRingtone(applicationContext, notification)
        r.play()
    }

    private fun updateTextOverview(text : String) {
        textViewOverview.text = text
    }

    private fun playStartSound(sound : Int) {
        val mp = MediaPlayer.create(this, sound)
        mp.start()
    }

}
