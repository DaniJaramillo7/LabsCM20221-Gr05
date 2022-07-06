package com.example.labscm20221_gr05

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate

class DatePickerFragment(val listener: (year: Int, month: Int, day: Int) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener  {
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(year, month+1, dayOfMonth)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(
            activity as Context,
            R.style.datePickerTheme,
            this,
            LocalDate.now().year,
            LocalDate.now().monthValue-1,
            LocalDate.now().dayOfMonth
        )
    }

}