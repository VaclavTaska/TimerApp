package com.taska.timer.util

import android.content.Context
import android.preference.PreferenceManager
import com.taska.timer.TimerRunning

class PrefUtil {
    companion object  {
        fun getTimerLenght(context : Context) : Int {
            return 1
        }

        private const val PREVIOUS_TIMER_LENGTH_ROUNDS_ID = "com.taska.timer.previous_timer_length_rounds"
        fun getPreviousTimerLengthRounds(context: Context) : Int{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(PREVIOUS_TIMER_LENGTH_ROUNDS_ID, 0)
        }
        fun setPreviousTimerLengthRounds(rounds : Int, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(PREVIOUS_TIMER_LENGTH_ROUNDS_ID, rounds)
            editor.apply()
        }

        private const val PREVIOUS_TIMER_LENGTH_SEDONDS_ID = "com.taska.timer.previous_timer_length_seconds"
        fun getPreviousTimerLengthSeconds(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SEDONDS_ID, 0)
        }
        fun setPreviousTimerLengthSeconds(context: Context, seconds : Long) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SEDONDS_ID, seconds)
            editor.apply()
        }

        private const val PREVIOUS_TIMER_LENGTH_MINUTES_ID = "com.taska.timer.previous_timer_length_minutes"
        fun getPreviousTimerLengthMinutes(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_MINUTES_ID, 0)
        }
        fun setPreviousTimerLengthMinutes(context: Context, minutes : Long) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_MINUTES_ID, minutes)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "com.taska.timer.timer_state"
        fun getTimerState(context: Context) : TimerRunning.TimerState {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID,0)
            return TimerRunning.TimerState.values()[ordinal]
        }
        fun setTimerState(context: Context, state : TimerRunning.TimerState) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID = "com.taska.timer.seconds_remaining"
        fun getSecondsRemaining(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }
        fun setSecondsRemaining(context: Context, seconds: Long){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }

    }
}