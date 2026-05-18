package com.example.tpo_desarrollo_apps_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterStep2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step2)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnCompletarRegistro = findViewById<Button>(R.id.btnCompletarRegistro)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val cbTerminos = findViewById<CheckBox>(R.id.cbTerminos)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnCompletarRegistro.setOnClickListener {
            if (validarCampos(etEmail, etPassword, etConfirmPassword, cbTerminos)) {
                val intent = Intent(this, HomeSubastasDisponibles::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun validarCampos(
        etEmail: EditText,
        etPassword: EditText,
        etConfirmPassword: EditText,
        cbTerminos: CheckBox
    ): Boolean {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        // Validar email no vacío
        if (email.isEmpty()) {
            Toast.makeText(this, "El email no puede estar vacío", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar formato de email (contiene @)
        if (!email.contains("@")) {
            Toast.makeText(this, "El email debe contener @", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar password no vacío
        if (password.isEmpty()) {
            Toast.makeText(this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar que password tenga mínimo 8 caracteres
        if (password.length < 8) {
            Toast.makeText(this, "La contraseña debe tener mínimo 8 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar que password contenga al menos un número
        if (!password.any { it.isDigit() }) {
            Toast.makeText(this, "La contraseña debe incluir al menos un número", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar confirm password no vacío
        if (confirmPassword.isEmpty()) {
            Toast.makeText(this, "Debe confirmar la contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar que las contraseñas coincidan
        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validar que se acepten los términos
        if (!cbTerminos.isChecked) {
            Toast.makeText(this, "Debe aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}