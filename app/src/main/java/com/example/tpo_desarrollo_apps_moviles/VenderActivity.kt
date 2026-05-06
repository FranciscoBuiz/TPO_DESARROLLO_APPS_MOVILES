package com.example.tpo_desarrollo_apps_moviles

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class VenderActivity : AppCompatActivity() {
    private val REQ_CAMERA = 100
    private var photoFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vender)

        configurarNavegacion()

        // Disparar cámara al tocar el recuadro de fotos
        findViewById<FrameLayout>(R.id.containerTakePhotos).setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "item_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(this, "$packageName.provider", photoFile!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(intent, REQ_CAMERA)
        }

        // Botón enviar con chequeo de red
        findViewById<Button>(R.id.btnEnviarSolicitud).setOnClickListener {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val isConnected = cm.activeNetwork != null
            if (isConnected) {
                Toast.makeText(this, "Solicitud enviada correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error: Sin conexión", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CAMERA && resultCode == RESULT_OK) {
            val bitmap = BitmapFactory.decodeFile(photoFile?.absolutePath)
            // Asegúrate de tener un ImageView con ID 'ivPreview' en activity_vender.xml
            findViewById<ImageView>(R.id.iv_pieza)?.setImageBitmap(bitmap)
        }
    }

    private fun configurarNavegacion() {
        findViewById<LinearLayout>(R.id.menu_inicio)?.setOnClickListener {
            startActivity(Intent(this, HomeSubastasDisponibles::class.java))
        }
        findViewById<LinearLayout>(R.id.menu_subasta)?.setOnClickListener {
            startActivity(Intent(this, DetalleSubastaActivity::class.java))
        }
    }
}