package com.haerokim.hate_alcohol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goto_normal_calc.setOnClickListener {
            startActivity(Intent(this, NormalCaculatorActivity::class.java))
        }

        goto_hate_calc.setOnClickListener {
            startActivity(Intent(this, HateCalculatorActivity::class.java))
        }

        var resultList : List<Result> = listOf(Result("20000원","2020.05.05"), Result("15000원", "2020.04.15"))

        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerAdapter(resultList)

        recyclerView = findViewById<RecyclerView>(R.id.main_recycler).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    data class Result(val price : String, val date:String)

    class RecyclerAdapter(private val myDataset: List<Result>) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        // Each data item is just a string in this case that is shown in a TextView.
        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): RecyclerAdapter.ViewHolder {
            // create a new view
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_result, parent, false)
            // set the view's size, margins, paddings and layout parameters
            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            val price = holder.view.findViewById<TextView>(R.id.result_price)
            price.text = myDataset[position].price

            val date = holder.view.findViewById<TextView>(R.id.result_date)
            date.text = myDataset[position].date

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = myDataset.size
    }
}
