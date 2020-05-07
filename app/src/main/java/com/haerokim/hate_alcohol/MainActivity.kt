package com.haerokim.hate_alcohol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    var resultList = mutableListOf<Result>()
    var storeList = mutableListOf<Result>() //Using Paper Library

    lateinit var current_date: Date
    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var getTime: String
    lateinit var getResult: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper.init(this)

        goto_normal_calc.setOnClickListener {
            startActivity(Intent(this, NormalCaculatorActivity::class.java))
        }

        goto_hate_calc.setOnClickListener {
            startActivity(Intent(this, HateCalculatorActivity::class.java))
        }

        storeList = Paper.book().read("data", mutableListOf<Result>())
        resultList = storeList
        viewAdapter = RecyclerAdapter(resultList)

        recyclerView = findViewById<RecyclerView>(R.id.main_recycler).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }

        if (intent.getStringExtra("result") != null) {
            //Get current time-date format
            current_date = Calendar.getInstance().time
            simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm")
            getTime = simpleDateFormat.format(current_date)

            getResult = intent.getStringExtra("result")
            resultList.add(0,Result(getResult, getTime))
            Paper.book().write("data", resultList)
            viewAdapter.notifyDataSetChanged()
        }

        storeList = Paper.book().read("data", mutableListOf<Result>())
        viewAdapter = RecyclerAdapter(storeList)

        recyclerView = findViewById<RecyclerView>(R.id.main_recycler).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }



        Log.d("count_item",viewAdapter.itemCount.toString())


    }

    data class Result(val price: String, val date: String)

    class RecyclerAdapter(private val myDataset: List<Result>) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_result, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val price = holder.view.findViewById<TextView>(R.id.result_price)
            price.text = myDataset[position].price

            val date = holder.view.findViewById<TextView>(R.id.result_date)
            date.text = myDataset[position].date
        }

        override fun getItemCount() = myDataset.size
    }
}
