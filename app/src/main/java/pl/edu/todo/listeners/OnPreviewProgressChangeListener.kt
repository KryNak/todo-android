package pl.edu.todo.listeners

import android.widget.ProgressBar
import android.widget.SeekBar

class OnPreviewProgressChangeListener(
    private val statsProgressbar: ProgressBar
) : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
        statsProgressbar.progress = progress
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        seekBar?.let {
            statsProgressbar.progress = it.progress
        }
    }

}