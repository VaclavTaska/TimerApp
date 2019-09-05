package com.taska.timer

import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.Test

internal class TimerRunningTest {

    fun prepareQuickTimerDataAdvancedOne() : QuickTimerData{
        val exercise1 = QuickTimerExercise(1,2,15)
        val exercise2 = QuickTimerExercise(1,2,15)
        val exercise3 = QuickTimerExercise(1,2,15)
        return QuickTimerData(3, mutableListOf(exercise1,exercise2,exercise3), 1, 10, true, "Advanced")
    }

    @Test
    fun timerRunOneRound() {
        SimpleTimer.quickTimer = prepareQuickTimerDataAdvancedOne()
        SimpleTimer.timerType = TimerType.AdvancedOne
        SimpleTimer.isQuickTimer = false
        val timerRunning : TimerRunning = TimerRunning()
        assertEquals(3, timerRunning.exerciseNum)
    }

}