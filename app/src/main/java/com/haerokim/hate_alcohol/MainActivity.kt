package com.haerokim.hate_alcohol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import javax.net.ssl.HandshakeCompletedEvent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goto_normal_calc.setOnClickListener {
            startActivity(Intent(this, NormalCaculatorActivity::class.java))
        }

        goto_hate_calc.setOnClickListener {
            startActivity(Intent(this, HateCalculatorActivity::class.java))
        }
    }
}
