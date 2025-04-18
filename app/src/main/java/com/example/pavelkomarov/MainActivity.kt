package com.example.pavelkomarov

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pavelkomarov.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val calendar = Calendar.getInstance()

    // Переменные для хранения выбранных даты и времени
    private var selectedDate: String = ""
    private var selectedTime: String = ""

    // Сохраняем Intent для сервиса
    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Инициализируем Intent для сервиса
        serviceIntent = Intent(this, NotificationService::class.java)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        // Кнопки для управления сервисом
        binding.startServiceButton.setOnClickListener {
            startService(serviceIntent)
        }

        binding.stopServiceButton.setOnClickListener {
            stopService(serviceIntent)
        }

        // Кнопки для диалогов
        binding.showAlertButton.setOnClickListener {
            showAlertDialog()
        }

        binding.showDatePickerButton.setOnClickListener {
            showDatePickerDialog()
        }

        binding.showTimePickerButton.setOnClickListener {
            showTimePickerDialog()
        }

        binding.showCustomDialogButton.setOnClickListener {
            showCustomDialog()
        }
    }

    // 1. AlertDialog
    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert_dialog_title)
        builder.setMessage(R.string.alert_dialog_message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        // Положительная кнопка (Да)
        builder.setPositiveButton(R.string.alert_dialog_positive) { dialog, _ ->
            Toast.makeText(this, getString(R.string.alert_dialog_response, "Да"), Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        // Отрицательная кнопка (Нет)
        builder.setNegativeButton(R.string.alert_dialog_negative) { dialog, _ ->
            Toast.makeText(this, getString(R.string.alert_dialog_response, "Нет"), Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        // Нейтральная кнопка (Позже)
        builder.setNeutralButton(R.string.alert_dialog_neutral) { dialog, _ ->
            Toast.makeText(this, getString(R.string.alert_dialog_response, "Позже"), Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    // 2. DatePickerDialog
    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                calendar.set(selectedYear, selectedMonth, selectedDayOfMonth)
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                selectedDate = dateFormat.format(calendar.time)
                updateDateTimeTextView()
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    // 3. TimePickerDialog
    private fun showTimePickerDialog() {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                calendar.set(Calendar.MINUTE, selectedMinute)
                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                selectedTime = timeFormat.format(calendar.time)
                updateDateTimeTextView()
            },
            hour,
            minute,
            true // 24-часовой формат
        )

        timePickerDialog.show()
    }

    // 4. Custom Dialog
    private fun showCustomDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)

        val saveButton = dialog.findViewById<android.widget.Button>(R.id.saveButton)
        val cancelButton = dialog.findViewById<android.widget.Button>(R.id.cancelButton)
        val userInput = dialog.findViewById<android.widget.EditText>(R.id.userInputEditText)

        saveButton.setOnClickListener {
            val userMessage = userInput.text.toString()
            if (userMessage.isNotEmpty()) {
                binding.customDialogResultText.text = getString(R.string.custom_dialog_result, userMessage)
            } else {
                binding.customDialogResultText.text = getString(R.string.custom_dialog_no_input)
            }
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    // Обновление TextView с датой и временем
    private fun updateDateTimeTextView() {
        if (selectedDate.isNotEmpty() || selectedTime.isNotEmpty()) {
            val dateStr = if (selectedDate.isNotEmpty()) selectedDate else "не выбрана"
            val timeStr = if (selectedTime.isNotEmpty()) selectedTime else "не выбрано"
            binding.dateTimeTextView.text = getString(R.string.date_time_format, dateStr, timeStr)
        } else {
            binding.dateTimeTextView.text = getString(R.string.date_time_not_set)
        }
    }
}