package com.example.tpo_desarrollo_apps_moviles

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class RegisterStep1Activity : AppCompatActivity() {
    private val REQ_CAMERA_FRENTE = 101
    private val REQ_CAMERA_DORSO = 102
    private var photoFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step1)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnEnviarDatos = findViewById<Button>(R.id.btnEnviarDatos)
        val btnUploadFrente = findViewById<LinearLayout>(R.id.btnUploadFrente)
        val btnUploadDorso = findViewById<LinearLayout>(R.id.btnUploadDorso)

        btnBack.setOnClickListener { 
            onBackPressedDispatcher.onBackPressed() 
        }

        btnUploadFrente.setOnClickListener { abrirCamara(REQ_CAMERA_FRENTE) }
        btnUploadDorso.setOnClickListener { abrirCamara(REQ_CAMERA_DORSO) }

        btnEnviarDatos.setOnClickListener {
            // Aquí podrías validar que las fotos se hayan tomado antes de pasar al siguiente paso
            val intent = Intent(this, RegisterStep2Activity::class.java)
            startActivity(intent)
        }
    }

    private fun abrirCamara(requestCode: Int) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "dni_${requestCode}_${System.currentTimeMillis()}.jpg"
        )
        
        val uri = FileProvider.getUriForFile(this, "$packageName.provider", photoFile!!)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val layout = if (requestCode == REQ_CAMERA_FRENTE) 
                findViewById<LinearLayout>(R.id.btnUploadFrente) 
            else 
                findViewById<LinearLayout>(R.id.btnUploadDorso)

            // Actualizamos el icono por la foto capturada
            val imageView = layout.getChildAt(0) as ImageView
            imageView.setImageBitmap(BitmapFactory.decodeFile(photoFile?.absolutePath))
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            
            Toast.makeText(this, "Foto cargada correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}