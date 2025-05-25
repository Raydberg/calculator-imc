package com.calculator_imc.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.calculator_imc.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        val etName = findViewById<EditText>(R.id.etName)
        btnStart.setOnClickListener {

            val etNameValue = etName.text.toString() //-> Te trae el valor que tiene el edit text

            if (etNameValue.isNotEmpty()) {
                Log.i("BtnInfo", "Valor de EditText $etNameValue")
                val intent = Intent(this, ResultActivity::class.java)
                // putExtra -> Envia un valor en el intent de forma clave-valor
                intent.putExtra("EXTRA_NAME", etNameValue)
                startActivity(intent)
            }

        }
    }
}