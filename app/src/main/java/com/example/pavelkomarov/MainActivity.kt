package com.example.pavelkomarov

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pavelkomarov.models.Item
import com.example.pavelkomarov.rv.ItemAdapter

class MainActivity : AppCompatActivity() {

    private val itemsList = listOf(
        Item(
            1,
            "Ноутбук",
            "Мощный ноутбук для работы и игр с высокой производительностью",
            R.drawable.red_bengal
        ),
        Item(
            2,
            "Смартфон",
            "Современный смартфон с отличной камерой и долгим временем работы",
            R.drawable.red_bengal
        ),
        Item(
            3,
            "Планшет",
            "Планшет с большим экраном для комфортного просмотра видео и чтения",
            R.drawable.red_bengal
        ),
        Item(
            4,
            "Наушники",
            "Беспроводные наушники с шумоподавлением для качественного звука",
            R.drawable.red_bengal
        ),
        Item(
            5,
            "Умные часы",
            "Умные часы с множеством функций для отслеживания здоровья и активности",
            R.drawable.red_bengal
        ),
        Item(
            6,
            "Фотоаппарат",
            "Профессиональный фотоаппарат для создания высококачественных снимков",
            R.drawable.red_bengal
        ),
        Item(
            7,
            "Игровая консоль",
            "Мощная игровая консоль с большой библиотекой игр",
            R.drawable.red_bengal
        ),
        Item(
            8,
            "Колонка",
            "Bluetooth колонка с мощным звуком и длительным временем работы",
            R.drawable.red_bengal
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemAdapter(itemsList)
    }
}