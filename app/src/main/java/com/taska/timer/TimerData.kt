package com.taska.timer

import android.content.Context
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

const val DATA_FILE_NAME_ADV_ONE = "dataFileAdvOne"
const val DATA_FILE_NAME_ADV_TWO = "dataFileAdvTwo"

enum class TimerType {
    Simple, AdvancedOne, AdvancedTwo
}

data class QuickTimerData(var rounds : Int? = null, var roundMin : Int? = null, var roundSec : Int? = null,
                          var restMin : Int? = null, var restSec : Int? = null)

data class QuickTimerExercise(var exercise : Int? = null, var roundMin : Int? = null, var roundSec : Int? = null)

object SimpleTimer {
    var quickTimer = QuickTimerData()
    val quickTimerExerciseList : MutableList<QuickTimerExercise> = mutableListOf()
    var isRestAfterRound = true
    var isQuickTimer = true
    var timerType = TimerType.Simple
    var timerName = "Advanced"
}

// TODO - Create file Writer
fun writeDataToFile(context: Context, filename : String){
    ObjectOutputStream(FileOutputStream(filename)).use { it -> it.writeObject(SimpleTimer) }
}

// TODO - Create File Reader
fun readDataFromFile(context: Context, filename: String) : Boolean  {
    if(File(filename).exists() && File(filename).length() > 0) {
        ObjectInputStream(FileInputStream(filename)).use { it ->
            when (val simpleTimer = it.readObject()) {
                is SimpleTimer -> {
                    SimpleTimer.quickTimer = simpleTimer.quickTimer
                    SimpleTimer.quickTimerExerciseList.clear()
                    SimpleTimer.quickTimerExerciseList.addAll(simpleTimer.quickTimerExerciseList)
                    SimpleTimer.isRestAfterRound = simpleTimer.isRestAfterRound
                    SimpleTimer.isQuickTimer = simpleTimer.isQuickTimer
                    SimpleTimer.timerType = simpleTimer.timerType
                    SimpleTimer.timerName = simpleTimer.timerName
                }
                else -> println()
            }
        }
        return true
    } else {
        return false
    }

}