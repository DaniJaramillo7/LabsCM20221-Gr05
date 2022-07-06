package com.example.labscm20221_gr05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PersonalDataActivity : AppCompatActivity() {

    private lateinit var grades: Array<String>
    private var birthDate: LocalDate? = null
    private var grade: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        findViewById<Button>(R.id.button_date).setOnClickListener {
            showDatePickerDialog()
        }
        grades = resources.getStringArray(R.array.grades)

        findViewById<Button>(R.id.next).setOnClickListener {
            onButtonNextTap();
        }
    }

    override fun onResume() {
        super.onResume()

        var adapterItems: ArrayAdapter<String> = ArrayAdapter(this, R.layout.dropdown_item, grades)
        var inputGrade: AutoCompleteTextView = findViewById(R.id.input_grade)
        inputGrade.setAdapter(adapterItems)
        inputGrade.setOnItemClickListener{ parent, view, position, id ->
            grade = grades[position]
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{year, month, day -> onDateSelected(year, month, day)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(year: Int, month: Int, day: Int) {
        birthDate = LocalDate.of(year, month, day)
    }

    fun onButtonNextTap() {

        val genres = resources.getStringArray(R.array.genres)

        val name = findViewById<EditText>(R.id.input_name).text.toString()
        val surname = findViewById<EditText>(R.id.input_surname).text.toString()
        var sexIndex: Int? = findViewById<RadioGroup>(R.id.sex_options).checkedRadioButtonId
        sexIndex = if (sexIndex == -1) null else sexIndex!! - 1;
        Log.d("Data", "Información personal")
        Log.d("Data", "$name $surname")
        if (sexIndex != null) Log.d("Data", "${genres[sexIndex]}")
        if (birthDate != null) Log.d("Data", "Nació el ${birthDate!!.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))}")
        if (grade != null) Log.d("Data", grade!!)
    }
}