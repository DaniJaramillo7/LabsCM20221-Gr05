package com.example.labscm20221_gr05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        findViewById<Button>(R.id.button_date).setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{year, month, day -> onDateSelected(year, month, day)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(year: Int, month: Int, day: Int) {
        Log.d("Selected date", "${year.toString()} - ${month.toString()} - ${day.toString()}")
    }
}