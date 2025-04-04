package com.example.pavelkomarov

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pavelkomarov.databinding.ActivityMainBinding
import com.example.pavelkomarov.models.SpinnerItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val spinnerItems = listOf(
        SpinnerItem(1, "Android", "Мобильная операционная система, разработанная Google"),
        SpinnerItem(2, "iOS", "Мобильная операционная система, разработанная Apple"),
        SpinnerItem(3, "Windows", "Операционная система для персональных компьютеров от Microsoft"),
        SpinnerItem(4, "macOS", "Операционная система для компьютеров Mac от Apple"),
        SpinnerItem(5, "Linux", "Семейство свободных операционных систем с открытым исходным кодом"),
        SpinnerItem(6, "Chrome OS", "Операционная система от Google, основанная на браузере Chrome")
    )

    private var selectedItem: SpinnerItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Настраиваем адаптер для Spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinnerItems.map { it.name }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        // Обработчик события выбора элемента в Spinner
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem = spinnerItems[position]
                updateSelectedItemInfo()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedItem = null
                updateSelectedItemInfo()
            }
        }

        // Обработчик нажатия на кнопку
        binding.btnConfirm.setOnClickListener {
            selectedItem?.let {
                Toast.makeText(
                    this@MainActivity,
                    "Выбрано: ${it.name}",
                    Toast.LENGTH_SHORT
                ).show()
            } ?: run {
                Toast.makeText(
                    this@MainActivity,
                    "Ничего не выбрано",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun updateSelectedItemInfo() {
        selectedItem?.let {
            binding.tvSelectedItem.text = it.name
            binding.tvItemDescription.text = it.description
        } ?: run {
            binding.tvSelectedItem.text = "[Нет выбранного элемента]"
            binding.tvItemDescription.text = ""
        }
    }
}