package com.example.pavelkomarov

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pavelkomarov.models.Product

class ProductsActivity : AppCompatActivity() {

    private lateinit var productsAdapter: ArrayAdapter<String>
    private val productsList = mutableListOf<Product>()
    private var nextProductId = 1

    // Предварительно заполненные данные для каждой категории
    private val fruitProducts = listOf(
        Product(1, "Яблоки Голден", 1),
        Product(2, "Яблоки Грэнни Смит", 1),
        Product(3, "Бананы", 1),
        Product(4, "Апельсины", 1),
        Product(5, "Груши", 1)
    )

    private val vegetableProducts = listOf(
        Product(6, "Помидоры", 2),
        Product(7, "Огурцы", 2),
        Product(8, "Картофель", 2),
        Product(9, "Морковь", 2),
        Product(10, "Лук", 2)
    )

    private val dairyProducts = listOf(
        Product(11, "Молоко", 3),
        Product(12, "Сыр", 3),
        Product(13, "Йогурт", 3),
        Product(14, "Творог", 3),
        Product(15, "Сметана", 3)
    )

    private val meatProducts = listOf(
        Product(16, "Говядина", 4),
        Product(17, "Свинина", 4),
        Product(18, "Курица", 4),
        Product(19, "Индейка", 4),
        Product(20, "Баранина", 4)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val categoryId = intent.getIntExtra("CATEGORY_ID", 0)
        val categoryName = intent.getStringExtra("CATEGORY_NAME") ?: "Продукты"

        // Установка названия категории
        val tvCategoryName = findViewById<TextView>(R.id.tvCategoryName)
        tvCategoryName.text = categoryName

        // Инициализация списка продуктов для выбранной категории
        when (categoryId) {
            1 -> productsList.addAll(fruitProducts)
            2 -> productsList.addAll(vegetableProducts)
            3 -> productsList.addAll(dairyProducts)
            4 -> productsList.addAll(meatProducts)
        }
        nextProductId = productsList.maxByOrNull { it.id }?.id?.plus(1) ?: 1

        // Настройка адаптера для списка
        val productNames = productsList.map { it.name }.toMutableList()
        productsAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            productNames
        )

        val productsListView = findViewById<ListView>(R.id.productsListView)
        productsListView.adapter = productsAdapter

        // Обработка долгого нажатия для удаления
        productsListView.setOnItemLongClickListener { _, _, position, _ ->
            showDeleteDialog(position)
            true
        }

        // Обработка нажатия кнопки добавления
        val btnAddProduct = findViewById<Button>(R.id.btnAddProduct)
        val etProductName = findViewById<EditText>(R.id.etProductName)

        btnAddProduct.setOnClickListener {
            val productName = etProductName.text.toString().trim()
            if (productName.isNotEmpty()) {
                val newProduct = Product(nextProductId++, productName, categoryId)
                productsList.add(newProduct)
                productNames.add(productName)
                productsAdapter.notifyDataSetChanged()
                etProductName.text.clear()
                Toast.makeText(this, "Продукт добавлен", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Введите название продукта", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDeleteDialog(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Удаление продукта")
            .setMessage("Вы уверены, что хотите удалить этот продукт?")
            .setPositiveButton("Да") { _, _ ->
                productsList.removeAt(position)
                productsAdapter.remove(productsAdapter.getItem(position))
                productsAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Продукт удален", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}