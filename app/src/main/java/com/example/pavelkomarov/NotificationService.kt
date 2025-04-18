package com.example.pavelkomarov

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.widget.Toast

class NotificationService : Service() {
    private var countDownTimer: CountDownTimer? = null
    private var isRunning = false

    override fun onCreate() {
        super.onCreate()
        countDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                Toast.makeText(
                    applicationContext,
                    "Осталось секунд: $secondsLeft",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFinish() {
                Toast.makeText(
                    applicationContext,
                    "Таймер завершен!",
                    Toast.LENGTH_LONG
                ).show()
                isRunning = false
                stopSelf()
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isRunning) {
            countDownTimer?.start()
            isRunning = true
            Toast.makeText(this, "Таймер запущен: 10 секунд", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Таймер уже запущен", Toast.LENGTH_SHORT).show()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
        isRunning = false
        Toast.makeText(this, "Таймер остановлен", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}