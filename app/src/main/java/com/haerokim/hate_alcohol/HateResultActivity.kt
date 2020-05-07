package com.haerokim.hate_alcohol

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    var hate_result: Int = 0

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

        hate_result =
            (total_anju / people) + (soju_sum * (soju_price / 8)) +
                    (beer_sum * (beer_price / 3)) + (somek_sum * (soju_price / 8 + beer_price / 2))

        Log.d("result_text", hate_result.toString())
        hate_sum_text_view.text = hate_result.toString() + "원"



        var result: String
        var clipboardManager: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clipData: ClipData

        hate_sum_text_view.setOnClickListener() {
            result = hate_sum_text_view.text.toString()
            clipData = ClipData.newPlainText("TOTAL", result)
            clipboardManager.primaryClip = clipData

            Snackbar.make(hate_result_layout, "복사되었습니다!", Snackbar.LENGTH_LONG).show()
        }


    }
}
