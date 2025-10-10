package com.appweek04

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CounterActivity : AppCompatActivity() {
    private  var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        val testViewCount = findViewById<TextView>(R.id.testViewCount)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonReset = findViewById<Button>(R.id.buttonReset)

        buttonMinus.setOnClickListener {
            count--
            testViewCount.text = count.toString()
            Log.d("KotlinWeek04App", "${count}")
        }
        buttonPlus.setOnClickListener {
            count++
            testViewCount.text = count.toString()
            Log.d("KotlinWeek04App", "${count}")
        }
        buttonReset.setOnClickListener {
            count = 0
            testViewCount.text = count.toString()
            Log.d("KotlinWeek04App", "${count}")
        }
    }
}