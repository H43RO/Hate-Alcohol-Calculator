package com.haerokim.hate_alcohol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_hate_calculator.*
import kotlinx.android.synthetic.main.activity_hate_calculator.count_member

class HateCalculatorActivity : AppCompatActivity() {
    var people = 0
    var soju_sum: Int = 0
    var beer_sum: Int = 0
    var somek_sum: Int = 0
    var total_anju: Int = 0
    var soju_price: Int = 0
    var beer_price: Int = 0
    var receipt_price:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hate_calculator)

        soju_picker.minValue = 0
        soju_picker.maxValue = 10

        beer_picker.minValue = 0
        beer_picker.maxValue = 10

        somek_picker.minValue = 0
        somek_picker.maxValue = 10

        Toast.makeText(this,"정보를 모두 입력해주세요",Toast.LENGTH_LONG).show()

        //술자리에 참여한 사람이 몇명인지 얻기 위한 SeekBar

        seekBar_hate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, count: Int, p2: Boolean) {
                count_member.setText(count.toString()+"명")
                people = count
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    //최종 결과 페이지로 이동하는 Floating Action Button onCLickListener

    fun onClickFinalButton(view : View) {
        soju_sum = soju_picker.value
        beer_sum = beer_picker.value
        somek_sum = somek_picker.value

        //만약 정보가 하나라도 기입되지 않았다면 EditText setError 발생

        if (people != 0 && price_soju.text.toString() != "" && price_beer.text.toString() != "" &&
            total_sum_hate.text.toString() != "" && receipt_sum_hate.text.toString() != "") {

            soju_price = Integer.parseInt(price_soju.text.toString())
            beer_price = Integer.parseInt(price_beer.text.toString())
            receipt_price = Integer.parseInt(receipt_sum_hate.text.toString())
            total_anju = Integer.parseInt(total_sum_hate.text.toString())

            //정보가 잘 입력이 되었다면 입력 데이터 실어서 Result Activity로 Intent

            val intent: Intent = Intent(this, HateResultActivity::class.java)
            intent.putExtra("people", people)
            intent.putExtra("total_anju", total_anju)
            intent.putExtra("soju_price", soju_price)
            intent.putExtra("beer_price", beer_price)
            intent.putExtra("soju_sum", soju_sum)
            intent.putExtra("beer_sum", beer_sum)
            intent.putExtra("somek_sum", somek_sum)
            intent.putExtra("receipt", receipt_price)

            startActivity(intent)

        } else {
            if(price_soju.text.toString() == ""){
                price_soju.setError("소주 가격을 입력해주세요")
            }

            if(price_beer.text.toString() == ""){
                price_beer.setError("맥주 가격을 입력해주세요")
            }

            if(total_sum_hate.text.toString() == ""){
                total_sum_hate.setError("안주 금액을 입력해주세요")
            }

            if(receipt_sum_hate.text.toString() == ""){
                receipt_sum_hate.setError("영수증에 찍힌 금액을 입력해주세요")
            }

            Toast.makeText(
                this@HateCalculatorActivity,
                "정보를 모두 입력해주세요",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}
