package com.taska.timer

import android.content.Context
import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity;
import android.view.WindowManager
import com.taska.timer.util.PrefUtil

import kotlinx.android.synthetic.main.activity_timer_running.*
import kotlinx.android.synthetic.main.content_timer_running.*
import kotlinx.serialization.ImplicitReflectionSerializer
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream

class TimerRunning : AppCompatActivity() {

    enum class TimerState {
        Stopped, Paused, Running
    }

    // TODO - Visible custom advanced exercise name.
    // TODO - Finish second advanced exercise.

    val f : NumberFormat = DecimalFormat("00")
    val timeCountDown : Long = 1000
    val roundsInit = SimpleTimer.quickTimer.rounds!!
    val exercisesInit = SimpleTimer.quickTimer.quickTimerExerciseList.size
    val minutesRestInit = SimpleTimer.quickTimer.restMin
    val secondsRestInit = SimpleTimer.quickTimer.restSec
    var rounds = roundsInit
    var exercises = exercisesInit
    var exerciseNum = 0
    var exerciseWithRest = exercisesInit * roundsInit * 2
    var minutesInit = getCurrentExerciseMinute()
    var secondsInit = getCurrentExerciseSeconds()
    var timeLeft : Long = 0
    var totalRounds = rounds * 2
    var initiateBell = false
    var isRestAfterRound = SimpleTimer.quickTimer.isRestAfterRound!!
    private lateinit var counterTime : CountDownTimer
    private var timerState = TimerState.Running

    @ImplicitReflectionSerializer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveInitTimeSetting(SimpleTimer.timerType)
        setContentView(R.layout.activity_timer_running)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setSupportActionBar(toolbar)
        updateRoundText()
        updateExerciseText()
        updateTextOverview("Let's work!")
        playStartSound(R.raw.gamestartcountdown)
        initialPause()
        updateButtons()
        // TODO - java.lang.RuntimeException: Unable to start activity ComponentInfo{com.taska.timer/com.taska.timer.TimerRunning}: java.io.FileNotFoundException: dataFileAdvOne (Read-only file system)


        buttonPlayTime.setOnClickListener {
            startTimer(timeLeft)
            timerState = TimerState.Running
            updateButtons()
        }

        buttonPauseTime.setOnClickListener {
            counterTime.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }


    }

    override fun onPause() {
        super.onPause()
        if(timerState == TimerState.Running) {
            counterTime?.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }
        //PrefUtil.setPreviousTimerSetSeconds(this, timeLeft)
        PrefUtil.setTimerState(this, timerState)
        PrefUtil.setSecondsRemaining(this, timeLeft)
    }


    private fun startTimer(time : Long) {
        if(initiateBell) {
            playStartSound(R.raw.belldingloud)
        }
        initiateBell = false
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
                when(SimpleTimer.isQuickTimer) {
                    true -> finishOfQuickTimer()
                    false -> finishOfAdvancedTimer()
                }
            }
        }.start()

    }

    private fun initialPause() {
        initiateBell = true
        val timeToWait : Long = 3200
        textViewTime.setText("Get Ready!")
        val counterTime = object : CountDownTimer(timeToWait, timeCountDown) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                startTimer(getQuickTime())
            }
        }.start()
    }

    private fun finishOfQuickTimer() {
        totalRounds -= 1
        when {
            totalRounds <= 1 -> {
                updateTextOverview("Time ended")
                playSound()
                rounds--
                updateRoundText()
            }
            totalRounds % 2 == 0 -> {
                rounds--
                updateRoundText()
                updateTextOverview("Let's work!")
                startTimer(getQuickTime())
            }
            else -> {
                startTimer(getQuickRestTime())
                updateTextOverview("Rest now")
            }
        }
    }

    private fun finishOfAdvancedTimer() {
        exerciseWithRest -= 1
        when {
            exerciseNum == exercisesInit -> {
                if(rounds <= 1) {
                    updateTextOverview("Time ended")
                    playSound()
                    rounds--
                    updateRoundText()
                } else {
                    exerciseNum = 0
                    rounds--
                    updateRoundText()
                    exercises = exercisesInit
                    updateExerciseText()
                    startTimer(getQuickRestTime())
                    updateTextOverview("Rest now")
                }
            }
            isRestAfterRound -> {
                if(exerciseNum == 0) {
                    minutesInit = getCurrentExerciseMinute()
                    secondsInit = getCurrentExerciseSeconds()
                    updateTextOverview("Let's work!")
                    updateExerciseText()
                    startTimer(getQuickTime())
                } else {
                    exercises -= 1
                    minutesInit = getCurrentExerciseMinute()
                    secondsInit = getCurrentExerciseSeconds()
                    updateTextOverview("Let's work!")
                    updateExerciseText()
                    startTimer(getQuickTime())
                }
            }
            else -> {
                when {
                    exerciseWithRest % 2 == 0 && exerciseNum == 0 -> {
                        minutesInit = getCurrentExerciseMinute()
                        secondsInit = getCurrentExerciseSeconds()
                        updateTextOverview("Let's work!")
                        startTimer(getQuickTime())
                    }
                    exerciseWithRest % 2 == 0 -> {
                        exercises -= 1
                        minutesInit = getCurrentExerciseMinute()
                        secondsInit = getCurrentExerciseSeconds()
                        updateTextOverview("Let's work!")
                        updateExerciseText()
                        startTimer(getQuickTime())
                    }
                    else -> {
                        updateTextOverview("Rest now")
                        startTimer(getQuickRestTime())
                    }
                }
            }
        }
    }


    private fun getQuickTime() : Long {
        var roundMinute : Long = (minutesInit)!!.toLong()
        var roundSecond : Long = (secondsInit)!!.toLong()
        return ((roundMinute * 60000) + (roundSecond * 1000))
    }

    private fun getQuickRestTime() : Long {
        var roundMinute : Long = (minutesRestInit)!!.toLong()
        var roundSecond : Long = (secondsRestInit)!!.toLong()
        return ((roundMinute * 60000) + (roundSecond * 1000))
    }

    private fun updateRoundText() {
        textViewRounds.text = "Round: ${rounds.toString()}"
    }

    private fun updateExerciseText() {
        textViewExercises.text = "Exercises: ${exercises.toString()}"
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

    private fun updateButtons() {
        when(timerState){
            TimerState.Paused -> {
                buttonPauseTime.isEnabled = false
                buttonPlayTime.isEnabled = true
            }
            TimerState.Running -> {
                buttonPauseTime.isEnabled = true
                buttonPlayTime.isEnabled = false
            }
            TimerState.Stopped -> {
                buttonPauseTime.isEnabled = true
                buttonPlayTime.isEnabled = true
            }
        }
    }

    private fun getCurrentExerciseMinute() : Int? {
        var exerciseMinutes : Int? = 0
        if(SimpleTimer.quickTimer.quickTimerExerciseList.size <= 1) {
            exerciseMinutes = SimpleTimer.quickTimer.quickTimerExerciseList[0].roundMin
        } else {
            exerciseMinutes = SimpleTimer.quickTimer.quickTimerExerciseList[exerciseNum].roundMin
            //exerciseNum++
        }
        return exerciseMinutes
    }

    private fun getCurrentExerciseSeconds() : Int? {
        var exerciseSeconds : Int? = 0
        if(SimpleTimer.quickTimer.quickTimerExerciseList.size <= 1) {
            exerciseSeconds = SimpleTimer.quickTimer.quickTimerExerciseList[0].roundSec
        } else {
            exerciseSeconds = SimpleTimer.quickTimer.quickTimerExerciseList[exerciseNum].roundSec
            exerciseNum++
        }
        return exerciseSeconds
    }

    @ImplicitReflectionSerializer
    private fun saveInitTimeSetting(timerType : TimerType) {
        when(timerType) {
            TimerType.Simple -> {
                PrefUtil.setPreviousSimpleTimerSetRounds(this, roundsInit!!)
                PrefUtil.setPreviousSimpleTimerSetMinutes(this, minutesInit!!)
                PrefUtil.setPreviousSimpleTimerSetSeconds(this, secondsInit!!)
                PrefUtil.setPreviousSimpleTimerSetRestMinutes(this, minutesRestInit!!)
                PrefUtil.setPreviousSimpleTimerSetRestSeconds(this, secondsRestInit!!)
            }
            TimerType.AdvancedOne -> {
                writeDataToFile(this, DATA_FILE_NAME_ADV_ONE)
            }
            TimerType.AdvancedTwo -> {

            }
        }

    }

    /*fun writeDataToFile(context: Context, filename : String){
        val file = File(context.filesDir, filename)
        ObjectOutputStream(FileOutputStream(file)).use { it -> it.writeObject(SimpleTimer) }

    }*/
}
