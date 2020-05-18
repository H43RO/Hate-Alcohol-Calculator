package com.haerokim.hate_alcohol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_hate_calculator.*
import kotlinx.android.synthetic.main.activity_hate_calculator.count_member
import kotlinx.android.synthetic.main.activity_normal_caculator.*

class HateCalculatorActivity : AppCompatActivity() {

    var people = 0
    var soju_sum: Int = 0
    var beer_sum: Int = 0
    var somek_sum: Int = 0
    var total_anju: Int = 0
    var soju_price: Int = 0
    var beer_price: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hate_calculator)

        soju_picker.minValue = 0
        soju_picker.maxValue = 10

        beer_picker.minValue = 0
        beer_picker.maxValue = 10

        somek_picker.minValue = 0
        somek_picker.maxValue = 10

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

        total_sum_hate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =
                if (total_sum_hate.text.toString() != "") {
                    total_anju = Integer.parseInt(total_sum_hate.text.toString())
                } else {
                    Toast.makeText(this@HateCalculatorActivity, "안주 금액을 입력해주세요", Toast.LENGTH_SHORT)
                        .show()
                }
        })

        final_button.setOnClickListener {
            soju_sum = soju_picker.value
            beer_sum = beer_picker.value
            somek_sum = somek_picker.value

            if (people != 0 && price_soju.text.toString() != "" && price_beer.text.toString() != "" && total_sum_hate.text.toString() != "") {
                soju_price = Integer.parseInt(price_soju.text.toString())
                beer_price = Integer.parseInt(price_beer.text.toString())

                val intent: Intent = Intent(this, HateResultActivity::class.java)
                intent.putExtra("people", people)
                intent.putExtra("total_anju", total_anju)
                intent.putExtra("soju_price", soju_price)
                intent.putExtra("beer_price", beer_price)
                intent.putExtra("soju_sum", soju_sum)
                intent.putExtra("beer_sum", beer_sum)
                intent.putExtra("somek_sum", somek_sum)

                startActivity(intent)

            } else {
                Toast.makeText(
                    this@HateCalculatorActivity,
                    "정보를 모두 입력해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }
}
