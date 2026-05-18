package com.example.tpo_desarrollo_apps_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        setupMenuOptions()
        configurarNavegacion()

        findViewById<Button>(R.id.btn_cerrar_sesion).setOnClickListener {
            mostrarConfirmacionCerrarSesion()
        }
    }

    private fun setupMenuOptions() {
        // Configuramos los textos de los items incluidos
        findViewById<LinearLayout>(R.id.opt_mis_subastas).apply {
            findViewById<TextView>(R.id.option_text).text = "MIS SUBASTAS"
            findViewById<android.widget.ImageView>(R.id.option_icon).setImageResource(android.R.drawable.ic_menu_agenda)
        }
        findViewById<LinearLayout>(R.id.opt_estadisticas).apply {
            findViewById<TextView>(R.id.option_text).text = "ESTADÍSTICAS"
            findViewById<android.widget.ImageView>(R.id.option_icon).setImageResource(android.R.drawable.ic_menu_sort_by_size)
        }
        findViewById<LinearLayout>(R.id.opt_medios_pago).apply {
            findViewById<TextView>(R.id.option_text).text = "MEDIOS DE PAGO"
            findViewById<android.widget.ImageView>(R.id.option_icon).setImageResource(android.R.drawable.ic_menu_save)
        }
        findViewById<LinearLayout>(R.id.opt_articulos_publicados).apply {
            findViewById<TextView>(R.id.option_text).text = "ARTÍCULOS PUBLICADOS"
            findViewById<android.widget.ImageView>(R.id.option_icon).setImageResource(android.R.drawable.ic_menu_upload)
        }
    }

    private fun configurarNavegacion() {
        findViewById<LinearLayout>(R.id.menu_inicio)?.setOnClickListener {
            val intent = Intent(this, HomeSubastasDisponibles::class.java)
            startActivity(intent)
            finish()
        }
        findViewById<LinearLayout>(R.id.menu_subasta)?.setOnClickListener {
             val intent = Intent(this, DetalleSubastaActivity::class.java)
             startActivity(intent)
             finish()
        }
        findViewById<LinearLayout>(R.id.menu_vender)?.setOnClickListener {
            val intent = Intent(this, VenderActivity::class.java)
            startActivity(intent)
            finish()
        }
        // Perfil ya está activo
    }

    private fun mostrarConfirmacionCerrarSesion() {
        AlertDialog.Builder(this)
            .setTitle("Cerrar Sesión")
            .setMessage("¿Estás seguro de que deseas cerrar sesión?")
            .setPositiveButton("Sí") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}
