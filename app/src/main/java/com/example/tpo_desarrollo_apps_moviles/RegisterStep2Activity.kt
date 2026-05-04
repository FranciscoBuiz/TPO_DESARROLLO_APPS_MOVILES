package com.example.tpo_desarrollo_apps_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RegisterStep2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step2)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnCompletarRegistro = findViewById<Button>(R.id.btnCompletarRegistro)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnCompletarRegistro.setOnClickListener {
            // Aquí iría la lógica final de registro
            val intent = Intent(this, HomeSubastasDisponibles::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}