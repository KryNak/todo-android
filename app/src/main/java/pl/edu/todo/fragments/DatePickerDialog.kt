package pl.edu.todo.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import java.time.LocalDate

class DatePickerDialog(var dateField: EditText): DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val now = LocalDate.now()
        return DatePickerDialog(requireContext(), this, now.year, now.monthValue - 1, now.dayOfMonth)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val date = LocalDate.of(year, month + 1, day)

        TimePickerDialog(dateField, date).show(
            requireActivity().supportFragmentManager,
            TimePickerDialog::class.java.name
        )
    }

}