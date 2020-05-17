package com.example.client_websock

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InstructionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instruction_activity)
        findViewById<TextView>(R.id.text_instruction).text = "Добро пожаловать в XG Predictor. Чтобы задать XG параметры, нажмите \"Настройка XG\". В открывшемся окне задайте параметры, если информации по некоторым из них нет, заполните нулями, или нажмите использовать тестовый набор."

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    override fun onBackPressed() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}