package com.example.tpo_desarrollo_apps_moviles

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class RegisterStep1Activity : AppCompatActivity() {
    private val REQ_CAMERA_FRENTE = 101
    private val REQ_CAMERA_DORSO = 102
    
    // Separamos los archivos para que no se pisen entre sí
    private var photoFileFrente: File? = null
    private var photoFileDorso: File? = null
    
    private var fotoCapturedFrente = false
    private var fotoCapturedDorso = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step1)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnEnviarDatos = findViewById<Button>(R.id.btnEnviarDatos)
        val btnUploadFrente = findViewById<LinearLayout>(R.id.btnUploadFrente)
        val btnUploadDorso = findViewById<LinearLayout>(R.id.btnUploadDorso)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etDocumento = findViewById<EditText>(R.id.etDocumento)
        val etDomicilio = findViewById<EditText>(R.id.etDomicilio)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnUploadFrente.setOnClickListener { abrirCamara(REQ_CAMERA_FRENTE) }
        btnUploadDorso.setOnClickListener { abrirCamara(REQ_CAMERA_DORSO) }

        btnEnviarDatos.setOnClickListener {
            if (validarCampos(etNombre, etApellido, etDocumento, etDomicilio)) {
                // Si todo está ok, pasa al paso 2
                val intent = Intent(this, RegisterStep2Activity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun abrirCamara(requestCode: Int) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        
        // Creamos el archivo temporal asignándolo al puntero correspondiente
        val archivoTemporal = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "dni_${requestCode}_${System.currentTimeMillis()}.jpg"
        )

        if (requestCode == REQ_CAMERA_FRENTE) {
            photoFileFrente = archivoTemporal
        } else {
            photoFileDorso = archivoTemporal
        }

        // Importante: Usamos "$packageName.provider" para que machee con el Manifest
        val uri = FileProvider.getUriForFile(this, "$packageName.provider", archivoTemporal)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

        // Otorgamos permisos de escritura temporales a la app de la cámara externa
        val grantFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        intent.addFlags(grantFlags)
        
        packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            .forEach { resolveInfo ->
                grantUriPermission(resolveInfo.activityInfo.packageName, uri, grantFlags)
            }

        // Verificamos si hay alguna app de cámara que pueda resolver el Intent
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, requestCode)
        } else {
            Toast.makeText(this, "No se encontró ninguna aplicación de cámara instalada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (resultCode == RESULT_OK) {
            val layout: LinearLayout
            val archivoAProcesar: File?

            if (requestCode == REQ_CAMERA_FRENTE) {
                fotoCapturedFrente = true
                layout = findViewById(R.id.btnUploadFrente)
                archivoAProcesar = photoFileFrente
            } else {
                fotoCapturedDorso = true
                layout = findViewById(R.id.btnUploadDorso)
                archivoAProcesar = photoFileDorso
            }

            // Validamos que el archivo exista antes de intentar decodificarlo
            if (archivoAProcesar != null && archivoAProcesar.exists()) {
                val imageView = layout.getChildAt(0) as ImageView
                val bitmap = BitmapFactory.decodeFile(archivoAProcesar.absolutePath)
                
                imageView.setImageBitmap(bitmap)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                
                Toast.makeText(this, "Foto cargada correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al recuperar la foto capturada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarCampos(etNombre: EditText, etApellido: EditText, etDocumento: EditText, etDomicilio: EditText): Boolean {
        if (etNombre.text.toString().trim().isEmpty()) {
            etNombre.error = "El nombre es obligatorio"
            etNombre.requestFocus()
            return false
        }
        if (etApellido.text.toString().trim().isEmpty()) {
            etApellido.error = "El apellido es obligatorio"
            etApellido.requestFocus()
            return false
        }
        if (etDocumento.text.toString().trim().isEmpty()) {
            etDocumento.error = "El documento es obligatorio"
            etDocumento.requestFocus()
            return false
        }
        if (etDomicilio.text.toString().trim().isEmpty()) {
            etDomicilio.error = "El domicilio es obligatorio"
            etDomicilio.requestFocus()
            return false
        }

        if (!fotoCapturedFrente) {
            Toast.makeText(this, "Debes capturar la foto del frente del DNI", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!fotoCapturedDorso) {
            Toast.makeText(this, "Debes capturar la foto del dorso del DNI", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}