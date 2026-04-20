package com.example.tpo_desarrollo_apps_moviles

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        
        // Animamos el progreso de 0 a 100 en 2 segundos
        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        progressAnimator.duration = 2000
        
        progressAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                // Cuando termina la animación, pasamos a la siguiente pantalla
                val intent = Intent(this@MainActivity, HomeSubastasDisponibles::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        progressAnimator.start()
    }
}