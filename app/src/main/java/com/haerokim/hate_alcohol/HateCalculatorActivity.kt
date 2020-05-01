package com.haerokim.hate_alcohol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_hate_calculator.*

class HateCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hate_calculator)
        var people = 0

        seekBar_hate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, count: Int, p2: Boolean) {
                count_member.setText(count.toString())
                people = count
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        soju_picker.minValue = 0
        soju_picker.maxValue = 10

        beer_picker.minValue = 0
        beer_picker.maxValue = 10

        somek_picker.minValue = 0
        somek_picker.maxValue = 10



    }
}
