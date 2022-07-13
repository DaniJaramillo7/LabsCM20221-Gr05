package com.example.labscm20221_gr05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.widget.doOnTextChanged
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PersonalDataActivity : AppCompatActivity() {

    private lateinit var grades: Array<String>
    private var birthDate: LocalDate? = null
    private var grade: String? = null

    private var formIsValid: Boolean = false

    val birthdayKey: String = "birthday"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)

        recoverState(savedInstanceState)

        findViewById<EditText>(R.id.input_name).doOnTextChanged { text, start, before, count ->
            evaluateFormIsValid();
        }

        findViewById<EditText>(R.id.input_surname).doOnTextChanged { text, start, before, count ->
            evaluateFormIsValid();
        }

        findViewById<Button>(R.id.button_date).setOnClickListener {
            showDatePickerDialog()
        }
        grades = resources.getStringArray(R.array.grades)

        findViewById<Button>(R.id.next).isEnabled = evaluateFormIsValid()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(birthDate != null) outState.putLong(birthdayKey, birthDate!!.toEpochDay())
    }

    private fun recoverState(savedInstanceState: Bundle?): Unit {
        if(savedInstanceState == null) return

        val savedBirthDateEpochDay = savedInstanceState.getLong(birthdayKey ,-1)
        if(!savedBirthDateEpochDay.equals(-1)) birthDate = LocalDate.ofEpochDay(savedBirthDateEpochDay)
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{year, month, day -> onDateSelected(year, month, day)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun evaluateFormIsValid(): Boolean {
        val nameIsValid = findViewById<EditText>(R.id.input_name).text.toString().trim().isNotEmpty()
        val surnameIsValid = findViewById<EditText>(R.id.input_surname).text.toString().trim().isNotEmpty()
        val birthdayIsValid = birthDate != null
        formIsValid =  nameIsValid && surnameIsValid && birthdayIsValid
        findViewById<Button>(R.id.next).isEnabled = formIsValid
        return formIsValid
    }

    fun onDateSelected(year: Int, month: Int, day: Int) {
        birthDate = LocalDate.of(year, month, day)
        evaluateFormIsValid()
    }

    fun onButtonNextTap() {

        val name = findViewById<EditText>(R.id.input_name).text.toString()
        val surname = findViewById<EditText>(R.id.input_surname).text.toString()

        var selectedSex = getSelectedSex()
        Log.d("Data", "Información personal")
        Log.d("Data", "$name $surname")
        if (selectedSex != null) Log.d("Data", selectedSex)
        if (birthDate != null) Log.d("Data", "Nació el ${birthDate!!.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))}")
        if (grade != null) Log.d("Data", grade!!)
    }

    private fun getSelectedSex(): String? {
        if (findViewById<RadioGroup>(R.id.sex_options).checkedRadioButtonId == -1) return null;
        val selectedSexRadio: RadioButton = findViewById(findViewById<RadioGroup>(R.id.sex_options).checkedRadioButtonId)
        return selectedSexRadio.text.toString()
    }
}