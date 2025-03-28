package com.example.pavelkomarov

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pavelkomarov.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Получаем переданное имя из Intent
        val fullName = intent.getStringExtra("fullName") ?: "Не указано"
        binding.tvFullName.text = getString(R.string.full_name, fullName)

        // Регистрируем лаунчер для получения результатов от ThirdActivity
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val scheduleInfo = data?.getStringExtra("scheduleInfo")
                binding.tvScheduleInfo.text = scheduleInfo

                // Показываем всплывающее сообщение
                Toast.makeText(this, "Время занятия успешно передано", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработчик для кнопки ввода информации
        binding.btnEnterInfo.setOnClickListener {
            val subject = binding.editTextSubject.text.toString()
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("fullName", fullName)
            intent.putExtra("subject", subject)
            activityResultLauncher.launch(intent)
        }

        // Кнопка возврата в MainActivity
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}