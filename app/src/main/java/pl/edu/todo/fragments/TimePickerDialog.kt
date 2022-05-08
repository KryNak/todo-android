package pl.edu.todo.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimePickerDialog(
    var dataField: EditText,
    val date: LocalDate
) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val nowTime = LocalDateTime.now()
        return TimePickerDialog(requireContext(), this, nowTime.hour, nowTime.minute, true)
    }

    override fun onTimeSet(p0: TimePicker?, hours: Int, minutes: Int) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val selectedLocalDateTime = LocalDateTime.of(date, LocalTime.of(hours, minutes))

        dataField.setText(formatter.format(selectedLocalDateTime))
    }
}