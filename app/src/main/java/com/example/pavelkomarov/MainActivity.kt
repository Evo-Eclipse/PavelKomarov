package com.example.pavelkomarov

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val submitButton = findViewById<Button>(R.id.submitButton)
        val commentEditText = findViewById<android.widget.EditText>(R.id.commentEditText)
        submitButton.setOnClickListener {
            val comment = commentEditText.text.toString().trim()
            if (comment.isNotEmpty()) {
                Toast.makeText(this, "Комментарий отправлен: $comment", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Введите комментарий", Toast.LENGTH_SHORT).show()
            }
        }
    }
}