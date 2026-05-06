package com.example.tpo_desarrollo_apps_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HomeSubastasDisponibles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_subastas_disponibles)

        // Configurar el botón "ENTRAR" que está en el item_subasta (el primero que encuentre)
        findViewById<Button>(R.id.btn_entrar)?.setOnClickListener {
            val intent = Intent(this, DetalleSubastaActivity::class.java)
            startActivity(intent)
        }

        configurarNavegacion()
    }

    private fun configurarNavegacion() {
        findViewById<LinearLayout>(R.id.menu_subasta)?.setOnClickListener {
            startActivity(Intent(this, DetalleSubastaActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.menu_vender)?.setOnClickListener {
            startActivity(Intent(this, VenderActivity::class.java))
        }
    }
}