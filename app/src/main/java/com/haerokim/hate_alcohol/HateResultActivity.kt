package com.haerokim.hate_alcohol

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_hate_result.*
import kotlinx.android.synthetic.main.activity_normal_caculator.*
import java.util.*

class HateResultActivity : AppCompatActivity() {

    var people = 0
    var soju_sum: Int = 0
    var beer_sum: Int = 0
    var somek_sum: Int = 0
    var total_anju: Int = 0
    var soju_price: Int = 0
    var beer_price: Int = 0
    var receipt: Int = 0

    var hate_result: Int = 0


    //SnackBar ActionButton Listener - Saving result at MainActivity

    inner class SaveButtonListener(val result: String) : View.OnClickListener {
        override fun onClick(v: View?) {
            val intent = Intent(this@HateResultActivity, MainActivity::class.java)
            intent.putExtra("hate_result", result)
            startActivity(intent)
        }

    }

    //저장 동작 없이 홈 화면으로 이동하기 위한 Floating Action Button onClickListener

    fun onClickHomeButton(view : View) {
       startActivity(Intent(this, MainActivity::class.java))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hate_result)

        val intent: Intent = getIntent()

        people = intent.extras.getInt("people")
        soju_sum = intent.extras.getInt("soju_sum")
        beer_sum = intent.extras.getInt("beer_sum")
        somek_sum = intent.extras.getInt("somek_sum")
        total_anju = intent.extras.getInt("total_anju")
        soju_price = intent.extras.getInt("soju_price")
        beer_price = intent.extras.getInt("beer_price")
        receipt = intent.extras.getInt("receipt")

        //Intent시 함께 넘어온 데이터를 통해 최종 결과값 계산하여 setText

        hate_result = (total_anju / people) + (soju_sum * (soju_price / 8)) + (beer_sum * (beer_price / 3)) +
                      (somek_sum * (soju_price / 8 + beer_price / 2))

        Log.d("result_text", hate_result.toString())
        hate_sum_text_view.text = hate_result.toString() + "원"
        another_people_result.text = ((receipt - hate_result) / (people - 1)).toString() + "원"



        //ClipBoard에 결과값을 복사

        var result: String
        var clipboardManager: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clipData: ClipData

        hate_sum_text_view.setOnClickListener() {
            result = hate_sum_text_view.text.toString()
            clipData = ClipData.newPlainText("TOTAL", result)
            clipboardManager.primaryClip = clipData

            Snackbar.make(hate_result_layout, "복사되었습니다!", Snackbar.LENGTH_LONG)
                .setAction("저장", SaveButtonListener(result))
                .setActionTextColor(Color.parseColor("#ffffff"))
                .show()
        }


    }
}
