package com.example.pavelkomarov

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // Equivalent to `return insets`
        }
        Log.d(TAG, "onCreate вызван")

        // Программный переход по нажатию кнопки
        val btnProgrammatic = findViewById<Button>(R.id.btnProgrammatic)
        btnProgrammatic.setOnClickListener {
            val fullName = findViewById<EditText>(R.id.editTextFullName).text.toString()
            val group = findViewById<EditText>(R.id.editTextGroup).text.toString()
            val age = findViewById<EditText>(R.id.editTextAge).text.toString()
            val grade = findViewById<EditText>(R.id.editTextGrade).text.toString()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("fullName", fullName)
            intent.putExtra("group", group)
            intent.putExtra("age", age)
            intent.putExtra("grade", grade)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart вызван")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume вызван")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause вызван")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop вызван")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy вызван")
    }

    // Метод для декларативного перехода (вызывается через onClick в разметке)
    fun goDeclaratively(view: View) {
        val fullName = findViewById<EditText>(R.id.editTextFullName).text.toString()
        val group = findViewById<EditText>(R.id.editTextGroup).text.toString()
        val age = findViewById<EditText>(R.id.editTextAge).text.toString()
        val grade = findViewById<EditText>(R.id.editTextGrade).text.toString()

        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("fullName", fullName)
        intent.putExtra("group", group)
        intent.putExtra("age", age)
        intent.putExtra("grade", grade)
        startActivity(intent)
    }
}
