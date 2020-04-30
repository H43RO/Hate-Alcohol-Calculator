package com.haerokim.hate_alcohol

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_hate_calculator.*
import kotlinx.android.synthetic.main.activity_hate_calculator.count_member
import kotlinx.android.synthetic.main.activity_hate_calculator.seekBar
import kotlinx.android.synthetic.main.activity_normal_caculator.*

class NormalCaculatorActivity : AppCompatActivity() {
    var people = 2
    var total: Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_caculator)


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, count: Int, p2: Boolean) {
                count_member.setText(count.toString())
                people = count
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        total_sum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =
                if (total_sum.text.toString() != "") {
                    total = Integer.parseInt(total_sum.text.toString())
                    total /= people
                    sum_text_view.text = total.toString() + "원"
                } else {
                    Toast.makeText(this@NormalCaculatorActivity, "금액을 입력해주세요", Toast.LENGTH_LONG)
                        .show()
                }
        })

        var result: String
        var clipboardManager: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clipData: ClipData

        sum_text_view.setOnClickListener() {
            result = sum_text_view.text.toString()
            clipData = ClipData.newPlainText("TOTAL", result)
            clipboardManager.primaryClip = clipData

            Toast.makeText(this, "복사되었습니다!", Toast.LENGTH_LONG).show()
            Log.d("Check","CHECKING")

        }

    }
}