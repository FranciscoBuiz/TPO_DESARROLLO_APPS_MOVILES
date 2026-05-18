package com.example.tpo_desarrollo_apps_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)

        btnLogin.setOnClickListener {
            if (validarCampos(etEmail, etPassword)) {
                val intent = Intent(this, HomeSubastasDisponibles::class.java)
                startActivity(intent)
                finish()
            }
        }

        tvForgotPassword.setOnClickListener {
            // Lógica para recuperar contraseña
        }

        tvCreateAccount.setOnClickListener {
            val intent = Intent(this, RegisterStep1Activity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.tvCreateAccount).setOnClickListener {
            startActivity(Intent(this, RegisterStep1Activity::class.java))
        }
    }

    private fun validarCampos(etEmail: EditText, etPassword: EditText): Boolean {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        // Validar que email no esté vacío
        if (email.isEmpty()) {
            Toast.makeText(this, "El email no puede estar vacío", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar formato de email 
        if (!email.contains("@gmail.com")) {
            Toast.makeText(this, "El email debe contener @", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar que password no esté vacío
        if (password.isEmpty()) {
            Toast.makeText(this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}