package com.example.tpo_desarrollo_apps_moviles

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
    private var fotoCapturada = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vender)

        // Si la actividad fue destruida por el sistema, recuperamos la ruta de la foto guardada
        if (savedInstanceState != null) {
            val savedPath = savedInstanceState.getString("KEY_PHOTO_PATH")
            if (savedPath != null) {
                photoFile = File(savedPath)
            }
            fotoCapturada = savedInstanceState.getBoolean("KEY_FOTO_CAPTURADA", false)
        }

        configurarNavegacion()

        // Disparar cámara al tocar el recuadro de fotos
        findViewById<FrameLayout>(R.id.containerTakePhotos).setOnClickListener {
            abrirCamaraDirecta()
        }

        // Botón enviar con chequeo de red y validación de foto
        findViewById<Button>(R.id.btnEnviarSolicitud).setOnClickListener {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val isConnected = cm.activeNetwork != null
            
            if (!isConnected) {
                Toast.makeText(this, "Error: Sin conexión a internet", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!fotoCapturada) {
                Toast.makeText(this, "Debes tomar una foto de la pieza antes de enviar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Solicitud enviada correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun abrirCamaraDirecta() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        
        // Creamos el archivo temporal
        val archivoTemporal = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES), 
            "item_${System.currentTimeMillis()}.jpg"
        )
        photoFile = archivoTemporal

        val uri = FileProvider.getUriForFile(this, "$packageName.provider", archivoTemporal)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

        // Otorgamos los permisos de URI correspondientes
        val grantFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        intent.addFlags(grantFlags)
        
        packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            .forEach { resolveInfo ->
                grantUriPermission(resolveInfo.activityInfo.packageName, uri, grantFlags)
            }

        // Lanzamos la actividad de la cámara directamente para evitar bloqueos por restricciones de visibilidad
        try {
            startActivityForResult(intent, REQ_CAMERA)
        } catch (e: Exception) {
            Toast.makeText(this, "No se pudo abrir la cámara: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    // Guardamos el estado por si Android destruye la activity en segundo plano al abrir la cámara
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        photoFile?.let {
            outState.putString("KEY_PHOTO_PATH", it.absolutePath)
        }
        outState.putBoolean("KEY_FOTO_CAPTURADA", fotoCapturada)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (requestCode == REQ_CAMERA) {
            if (resultCode == RESULT_OK) {
                val archivoFoto = photoFile
                
                if (archivoFoto != null && archivoFoto.exists()) {
                    val bitmap = BitmapFactory.decodeFile(archivoFoto.absolutePath)
                    // Nota: Asegúrate de que el layout tenga iv_pieza si quieres mostrar el preview
                    val ivPieza = findViewById<ImageView>(R.id.iv_pieza)
                    
                    if (ivPieza != null) {
                        ivPieza.setImageBitmap(bitmap)
                        ivPieza.scaleType = ImageView.ScaleType.CENTER_CROP
                        fotoCapturada = true
                        Toast.makeText(this, "Foto del artículo cargada", Toast.LENGTH_SHORT).show()
                    } else {
                        // Si no hay iv_pieza, igual marcamos como capturada si el archivo existe
                        fotoCapturada = true
                        Toast.makeText(this, "Foto capturada correctamente", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Error al procesar el archivo de imagen", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Captura de foto cancelada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun configurarNavegacion() {
        findViewById<LinearLayout>(R.id.menu_inicio)?.setOnClickListener {
            val intent = Intent(this, HomeSubastasDisponibles::class.java)
            startActivity(intent)
        }
        
        findViewById<LinearLayout>(R.id.menu_subasta)?.setOnClickListener {
            val intent = Intent(this, DetalleSubastaActivity::class.java)
            startActivity(intent)
        }

        findViewById<LinearLayout>(R.id.menu_perfil)?.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
    }
}
