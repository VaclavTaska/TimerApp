package com.taska.timer

import android.app.ActionBar
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_advanced_one.*
import kotlinx.android.synthetic.main.content_advanced_one.*
import java.util.*
import kotlin.collections.ArrayList

class AdvancedOneActivity : AppCompatActivity() {

    var listDynamicObj = ArrayList<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advanced_one)
        setSupportActionBar(toolbar)
        initNumberPickers()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        numberPickerExercisesAdvOne.setOnValueChangedListener{ picker, oldVal, newVal ->
            textViewTest.text = "Exercise value is $newVal"
            updateExercisePickers(newVal)
        }
    }

    private fun initNumberPickers(){
        numberPickerRoundsAdvOne.minValue = 1
        numberPickerRoundsAdvOne.maxValue = 99
        numberPickerExercisesAdvOne.minValue = 1
        numberPickerExercisesAdvOne.maxValue = 20
        numberPickerBreakMinutesAdvOne.minValue = 0
        numberPickerBreakMinutesAdvOne.maxValue = 60
        numberPickerBreakSecondsAdvOne.minValue = 0
        numberPickerBreakSecondsAdvOne.maxValue = 59
    }

    private fun updateExercisePickers(pickerNumb : Int) {
        removeOldExercisePickers()
        for(i in 1..pickerNumb) {
            val dynamicText = initDynamicText(i)
            val dynamicHorLayout = LinearLayout(ContextThemeWrapper(this, R.style.DynamicLinearLayoutTheme))
            val dynamicMinPicker = initDynamicPicker(true)
            val dynamicSecPicker = initDynamicPicker(false)
            sss.addView(dynamicText)
            sss.addView(dynamicHorLayout)
            dynamicHorLayout.addView(dynamicMinPicker)
            dynamicHorLayout.addView(dynamicSecPicker)
            listDynamicObj.add(dynamicText)
            listDynamicObj.add(dynamicHorLayout)
            listDynamicObj.add(dynamicMinPicker)
            listDynamicObj.add(dynamicSecPicker)
            dynamicHorLayout.setMargins(160,10,10,10)
            dynamicMinPicker.setMargins(80,10,10,10)
            dynamicSecPicker.setMargins(160,10,10,10)
        }
    }

    private fun removeOldExercisePickers() {
        for(i in listDynamicObj) {
            when(i) {
                is TextView -> sss.removeView(i)
                is NumberPicker -> sss.removeView(i)
                is LinearLayout -> sss.removeView(i)
            }
        }
        listDynamicObj.clear()
    }

    private fun initDynamicText(exerciseNumber : Int) : TextView {
        var dText = TextView(this)
        dText.textSize = 36f
        dText.setTextColor(Color.WHITE)
        dText.text = "Exercise $exerciseNumber"
        return dText
    }

    private fun initDynamicPicker(isPickerForMinutes : Boolean) : NumberPicker{
        var dPick = NumberPicker(ContextThemeWrapper(this, R.style.NumberPickerTheme))
        if(isPickerForMinutes){
            dPick.maxValue = 60
            dPick.minValue = 0
        } else {
            dPick.maxValue = 59
            dPick.minValue = 0
        }
        return dPick
    }

    fun View.setMargins(
        left: Int? = null,
        top: Int? = null,
        right: Int? = null,
        bottom: Int? = null
    ) {
        val lp = layoutParams as? ViewGroup.MarginLayoutParams
            ?: return

        lp.setMargins(
            left ?: lp.leftMargin,
            top ?: lp.topMargin,
            right ?: lp.rightMargin,
            bottom ?: lp.rightMargin
        )

        layoutParams = lp
    }

}
