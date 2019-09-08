package com.taska.timer.util

import android.content.Context
import android.preference.PreferenceManager
import com.taska.timer.TimerRunning

class PrefUtil {
    companion object  {
        fun getTimerLenght(context : Context) : Int {
            return 1
        }

        //#########################
        // Main menu data
        //#########################
        private const val ADVANCED_ONE_EXERCICE_NAME = "com.taska.timer.advanced_one_exercise_name"
        fun getAdvancedExerciseOneName(context: Context) : String {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(ADVANCED_ONE_EXERCICE_NAME, "Your custom exercise")
        }
        fun setAdvancedExerciseOneName(context: Context, name: String){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(ADVANCED_ONE_EXERCICE_NAME, name)
            editor.apply()
        }

        private const val ADVANCED_TWO_EXERCICE_NAME = "com.taska.timer.advanced_two_exercise_name"
        fun getAdvancedExerciseTwoName(context: Context) : String {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(ADVANCED_TWO_EXERCICE_NAME, "Your custom exercise")
        }
        fun setAdvancedExerciseTwoName(context: Context, name: String){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(ADVANCED_TWO_EXERCICE_NAME, name)
            editor.apply()
        }


        //#########################
        // Simple Timer Data
        //#########################
        private const val PREVIOUS_SIMPLE_TIMER_SET_ROUNDS_ID = "com.taska.timer.previous_timer_set_rounds"
        fun getPreviousSimpleTimerSetRounds(context: Context) : Int{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(PREVIOUS_SIMPLE_TIMER_SET_ROUNDS_ID, 0)
        }
        fun setPreviousSimpleTimerSetRounds(context: Context, rounds : Int) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(PREVIOUS_SIMPLE_TIMER_SET_ROUNDS_ID, rounds)
            editor.apply()
        }

        private const val PREVIOUS_SIMPLE_TIMER_SET_SEDONDS_ID = "com.taska.timer.previous_timer_set_seconds"
        fun getPreviousSimpleTimerSetSeconds(context: Context) : Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(PREVIOUS_SIMPLE_TIMER_SET_SEDONDS_ID, 0)
        }
        fun setPreviousSimpleTimerSetSeconds(context: Context, seconds : Int) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(PREVIOUS_SIMPLE_TIMER_SET_SEDONDS_ID, seconds)
            editor.apply()
        }

        private const val PREVIOUS_SIMPLE_TIMER_SET_MINUTES_ID = "com.taska.timer.previous_timer_set_minutes"
        fun getPreviousSimpleTimerSetMinutes(context: Context) : Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(PREVIOUS_SIMPLE_TIMER_SET_MINUTES_ID, 0)
        }
        fun setPreviousSimpleTimerSetMinutes(context: Context, minutes : Int) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(PREVIOUS_SIMPLE_TIMER_SET_MINUTES_ID, minutes)
            editor.apply()
        }

        private const val PREVIOUS_SIMPLE_TIMER_SET_REST_SEDONDS_ID = "com.taska.timer.previous_timer_set_rest_seconds"
        fun getPreviousSimpleTimerSetRestSeconds(context: Context) : Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(PREVIOUS_SIMPLE_TIMER_SET_REST_SEDONDS_ID, 0)
        }
        fun setPreviousSimpleTimerSetRestSeconds(context: Context, seconds : Int) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(PREVIOUS_SIMPLE_TIMER_SET_REST_SEDONDS_ID, seconds)
            editor.apply()
        }

        private const val PREVIOUS_SIMPLE_TIMER_SET_REST_MINUTES_ID = "com.taska.timer.previous_timer_set_rest_minutes"
        fun getPreviousSimpleTimerSetRestMinutes(context: Context) : Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(PREVIOUS_SIMPLE_TIMER_SET_REST_MINUTES_ID, 0)
        }
        fun setPreviousSimpleTimerSetRestMinutes(context: Context, minutes : Int) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(PREVIOUS_SIMPLE_TIMER_SET_REST_MINUTES_ID, minutes)
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