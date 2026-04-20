package com.example.tpo_desarrollo_apps_moviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeSubastasDisponibles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_subastas_disponibles)

        // For simplicity, we find the first "ENTRAR" button and make it navigate to details
        // In a real app, you'd use a RecyclerView
        findViewById<Button>(R.id.btn_entrar)?.setOnClickListener {
            val intent = Intent(this, DetalleSubastaActivity::class.java)
            startActivity(intent)
        }
    }
}