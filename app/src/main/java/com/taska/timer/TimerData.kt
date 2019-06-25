package com.taska.timer

data class QuickTimerData(var rounds : Int? = null, var roundMin : Int? = null, var roundSec : Int? = null,
                          var restMin : Int? = null, var restSec : Int? = null)

object SimpleTimer {
    val quickTimer = QuickTimerData()
}