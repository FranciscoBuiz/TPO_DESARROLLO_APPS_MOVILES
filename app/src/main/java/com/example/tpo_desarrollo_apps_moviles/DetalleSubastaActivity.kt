package com.example.tpo_desarrollo_apps_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class DetalleSubastaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_subasta)

        // Llamamos a la navegación para que el menú funcione en esta pantalla
        configurarNavegacion()
    }

    private fun configurarNavegacion() {
        findViewById<LinearLayout>(R.id.menu_inicio)?.setOnClickListener {
            val intent = Intent(this, HomeSubastasDisponibles::class.java)
            startActivity(intent)
        }
        findViewById<LinearLayout>(R.id.menu_vender)?.setOnClickListener {
            val intent = Intent(this, VenderActivity::class.java)
            startActivity(intent)
        }
        // Agregamos lógica para "Subasta" si quieres que refresque o vaya a otra lista
    }
}