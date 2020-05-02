package com.haerokim.hate_alcohol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_hate_result.*

class HateResultActivity : AppCompatActivity() {

    var people = 0
    var soju_sum: Int = 0
    var beer_sum: Int = 0
    var somek_sum: Int = 0
    var total_anju: Int = 0
    var soju_price: Int = 0
    var beer_price: Int = 0

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


        button.setOnClickListener {
            Log.d("intent_test2", people.toString())
            Log.d("intent_test2", total_anju.toString())
            Log.d("intent_test2", soju_price.toString())
            Log.d("intent_test2", beer_price.toString())
            Log.d("intent_test2", soju_sum.toString())
            Log.d("intent_test2", beer_sum.toString())
            Log.d("intent_test2", somek_sum.toString())
        }


    }
}
