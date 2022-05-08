package pl.edu.todo.listeners

import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView

class OnPreviewProgressChangeListener(
    private val statsProgressbar: ProgressBar,
    private val percentageLabel: TextView
) : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
        statsProgressbar.progress = progress
        percentageLabel.text = progress.toString()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        seekBar?.let {
            statsProgressbar.progress = it.progress
            percentageLabel.text = it.progress.toString()
        }
    }

}