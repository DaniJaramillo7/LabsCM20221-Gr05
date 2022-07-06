package com.example.labscm20221_gr05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button

class PersonalDataActivity : AppCompatActivity() {

    private lateinit var grades: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        findViewById<Button>(R.id.button_date).setOnClickListener {
            showDatePickerDialog()
        }
        grades = resources.getStringArray(R.array.grades)
    }

    override fun onResume() {
        super.onResume()

        var adapterItems: ArrayAdapter<String> = ArrayAdapter(this, R.layout.dropdown_item, grades)
        var inputGrade: AutoCompleteTextView = findViewById(R.id.input_grade)
        inputGrade.setAdapter(adapterItems)
        inputGrade.setOnItemClickListener{ parent, view, position, id ->
            Log.d("position", grades[position])
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