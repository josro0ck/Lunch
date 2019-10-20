package com.josro0ck.lunch.ui

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.EditText
import android.app.Activity
import android.app.DatePickerDialog.OnDateSetListener
import android.view.View
import androidx.fragment.app.FragmentActivity
import java.util.*


class EditTextDatePicker(private val _context: FragmentActivity?, editTextViewID: EditText) : View.OnClickListener, OnDateSetListener {
    internal var _editText: EditText
    private var _day: Int = 0
    private var _month: Int = 0
    private var _birthYear: Int = 0

    init {
        val act = _context as Activity
        this._editText = editTextViewID
        this._editText.setOnClickListener(this)
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        _birthYear = year
        _month = monthOfYear
        _day = dayOfMonth
        updateDisplay()
    }

    override fun onClick(v: View) {
        val calendar = Calendar.getInstance(TimeZone.getDefault())

        val dialog = DatePickerDialog(
            _context, this,
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()

    }

    // updates the date in the birth date EditText
    private fun updateDisplay() {

        _editText.setText(
            StringBuilder()
                // Month is 0 based so add 1
                .append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" ")
        )
    }
}