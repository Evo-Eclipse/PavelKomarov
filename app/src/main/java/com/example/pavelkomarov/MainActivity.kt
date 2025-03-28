package com.example.pavelkomarov

import android.os.Bundle
import android.view.View
import android.widget.Button
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

        // Динамически добавляем второй фрагмент
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, SecondFragment())
                .commit()
        }

        // Обработчики для кнопок навигации
        findViewById<Button>(R.id.btnShowFirst).setOnClickListener {
            navigateToFirstFragment()
        }

        findViewById<Button>(R.id.btnShowSecond).setOnClickListener {
            navigateToSecondFragment()
        }

        findViewById<Button>(R.id.btnShowThird).setOnClickListener {
            navigateToThirdFragment()
        }
    }

    fun navigateToFirstFragment() {
        // Первый фрагмент статический, поэтому переключаемся на него через видимость
        findViewById<View>(R.id.fragment_static).visibility = View.VISIBLE
        findViewById<View>(R.id.fragment_container).visibility = View.GONE
        findViewById<View>(R.id.fragment_container_view).visibility = View.GONE
    }

    fun navigateToSecondFragment() {
        findViewById<View>(R.id.fragment_static).visibility = View.GONE
        findViewById<View>(R.id.fragment_container).visibility = View.VISIBLE
        findViewById<View>(R.id.fragment_container_view).visibility = View.GONE
    }

    fun navigateToThirdFragment() {
        findViewById<View>(R.id.fragment_static).visibility = View.GONE
        findViewById<View>(R.id.fragment_container).visibility = View.GONE
        findViewById<View>(R.id.fragment_container_view).visibility = View.VISIBLE
    }
}