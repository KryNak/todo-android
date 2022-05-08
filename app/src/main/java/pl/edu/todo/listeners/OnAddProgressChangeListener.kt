package pl.edu.todo.listeners

import android.widget.SeekBar
import android.widget.TextView

class OnAddProgressChangeListener(var progressText: TextView): SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        p0?.let {
            progressText.text = "$p1 %"
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        p0?.let {
            progressText.text = "${it.progress} %"
        }
    }
}