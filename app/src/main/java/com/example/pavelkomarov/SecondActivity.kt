package com.example.pavelkomarov

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pavelkomarov.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val TAG = "SecondActivity"
    /* private var _binding: ActivitySecondBinding? = null
    private val binding get() = _binding!! */
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /* _binding = ActivitySecondBinding.inflate(layoutInflater) */
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(TAG, "onCreate вызван")

        val fullName = intent.getStringExtra("fullName") ?: "Не указано"
        val group = intent.getStringExtra("group") ?: "Не указано"
        val age = intent.getStringExtra("age") ?: "Не указано"
        val grade = intent.getStringExtra("grade") ?: "Не указано"

        binding.tvFullName.text = getString(R.string.full_name, fullName)
        binding.tvGroup.text = getString(R.string.group_number, group)
        binding.tvAge.text = getString(R.string.age, age)
        binding.tvGrade.text = getString(R.string.grade, grade)

        // Кнопка возврата в MainActivity
        binding.btnBack.setOnClickListener {
            finish()
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
}