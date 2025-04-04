package com.example.pavelkomarov

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pavelkomarov.models.Category

class MainActivity : AppCompatActivity() {

    private val categories = listOf(
        Category(1, "Фрукты"),
        Category(2, "Овощи"),
        Category(3, "Молочные продукты"),
        Category(4, "Мясо")
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

        val listView = findViewById<ListView>(R.id.categoryListView)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            categories.map { it.name }
        )
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val category = categories[position]
            val intent = Intent(this, ProductsActivity::class.java)
            intent.putExtra("CATEGORY_ID", category.id)
            intent.putExtra("CATEGORY_NAME", category.name)
            startActivity(intent)
        }
    }
}