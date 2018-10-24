package edu.pavel.courceproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(TestView(this))

//        val but = findViewById<Button>(R.id.button)
//
//        but.setOnClickListener { v ->
//            val tView = findViewById<TextView>(R.id.textView)
//            tView.text = "New text!"
//        }
    }
}
