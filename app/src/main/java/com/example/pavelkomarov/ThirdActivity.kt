package com.example.pavelkomarov

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.thirdRoot)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Получаем переданные данные
        val fullName = intent.getStringExtra("fullName") ?: "Не указано"
        val subject = intent.getStringExtra("subject") ?: "Не указано"

        // Отображаем информацию
        findViewById<TextView>(R.id.tvUserInfo).text =
            getString(R.string.user_subject_info, fullName, subject)

        // Обработчик кнопки OK
        findViewById<Button>(R.id.btnOk).setOnClickListener {
            val day = findViewById<EditText>(R.id.editTextDay).text.toString()
            val time = findViewById<EditText>(R.id.editTextTime).text.toString()
            val comments = findViewById<EditText>(R.id.editTextComments).text.toString()

            // Формируем строку с информацией о расписании
            val scheduleInfo = getString(
                R.string.schedule_info,
                subject, day, time, comments
            )

            // Создаем интент с результатом
            val resultIntent = Intent()
            resultIntent.putExtra("scheduleInfo", scheduleInfo)

            // Устанавливаем результат и завершаем активность
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}