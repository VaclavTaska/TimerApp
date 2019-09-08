package com.taska.timer

import android.content.Context
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.stringify
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

const val DATA_FILE_NAME_ADV_ONE = "datafileadvone.txt"
const val DATA_FILE_NAME_ADV_TWO = "dataFileAdvTwo"

enum class TimerType {
    Simple, AdvancedOne, AdvancedTwo
}

/*
@Serializable
data class QuickTimerData(var rounds : Int? = null, var roundMin : Int? = null, var roundSec : Int? = null,
                          var restMin : Int? = null, var restSec : Int? = null)*/

@Serializable
data class QuickTimerData(var rounds : Int? = null, var quickTimerExerciseList : MutableList<QuickTimerExercise> = mutableListOf(),
                          var restMin : Int? = null, var restSec : Int? = null, var isRestAfterRound : Boolean? = true, var timerName : String? = "Advanced")

@Serializable
data class QuickTimerExercise(var exercise : Int? = null, var roundMin : Int? = null, var roundSec : Int? = null)


object SimpleTimer {
    var quickTimer = QuickTimerData()
    //var isRestAfterRound = true
    var isQuickTimer = true
    var timerType = TimerType.Simple
    //var timerName = "Advanced"
}

@ImplicitReflectionSerializer
fun writeDataToFile(context: Context, filename : String){
    //val file = File(context.filesDir, filename)
    val json = Json(JsonConfiguration.Stable)
    val dToWrite = json.stringify(SimpleTimer.quickTimer)
    context.openFileOutput(filename, Context.MODE_PRIVATE).use {
        it.write(dToWrite.toByteArray())
    }
    //file.writeText(dToWrite)
}

@ExperimentalStdlibApi
fun readDataFromFile(context: Context, filename: String) : Boolean  {
    val file = File(context.filesDir, filename)
    if(file.exists() && file.length() != 0L) {
        val json = Json(JsonConfiguration.Stable)
        val dToRead = file.readBytes().decodeToString()
        val readData = json.parse(QuickTimerData.serializer(), dToRead)
        SimpleTimer.quickTimer = readData
        return true
    } else {
        return false
    }

}