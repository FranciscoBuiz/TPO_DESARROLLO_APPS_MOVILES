package com.example.tpo_desarrollo_apps_moviles

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
    private val MIN_FOTOS = 4
    private var photoFile: File? = null
    private val photosList = mutableListOf<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vender)

        val containerPhotos = findViewById<FrameLayout>(R.id.containerTakePhotos)
        val btnEnviar = findViewById<Button>(R.id.btnEnviarSolicitud)

        // Al tocar el recuadro, abrimos la cámara
        containerPhotos.setOnClickListener { abrirCamara() }

        // Al enviar, verificamos los datos
        btnEnviar.setOnClickListener { validarYEnviar() }
    }

    private fun abrirCamara() {
        if (photosList.size >= 10) {
            Toast.makeText(this, "Máximo 10 fotos permitidas", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "foto_item_${System.currentTimeMillis()}.jpg"
        )

        val uri = FileProvider.getUriForFile(this, "$packageName.provider", photoFile!!)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, REQ_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CAMERA && resultCode == RESULT_OK) {
            photoFile?.let {
                photosList.add(it)
                agregarFotoAlPreview(it)
                Toast.makeText(
                    this,
                    "Foto ${photosList.size} de $MIN_FOTOS añadida",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun agregarFotoAlPreview(file: File) {
        val photosContainer = findViewById<LinearLayout>(R.id.photosContainer)
        val imageView = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(100, 100).apply {
                setMargins(8, 0, 8, 0)
            }
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
        }
        photosContainer.addView(imageView)
    }

    private fun validarYEnviar() {
        val nombre = findViewById<EditText>(R.id.etNombreObra).text.toString().trim()
        val artista = findViewById<EditText>(R.id.etArtista).text.toString().trim()
        val descripcion = findViewById<EditText>(R.id.etDescripcion).text.toString().trim()
        val procedencia = findViewById<EditText>(R.id.etProcedencia).text.toString().trim()
        val declaracion = findViewById<CheckBox>(R.id.cbDeclaracion).isChecked

        when {
            photosList.size < MIN_FOTOS -> {
                Toast.makeText(
                    this,
                    "Se requieren al menos $MIN_FOTOS fotos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            nombre.isEmpty() -> {
                Toast.makeText(this, "Ingresa el nombre de la obra", Toast.LENGTH_SHORT).show()
            }
            artista.isEmpty() -> {
                Toast.makeText(this, "Ingresa el nombre del artista", Toast.LENGTH_SHORT).show()
            }
            descripcion.isEmpty() -> {
                Toast.makeText(this, "Ingresa la descripción técnica", Toast.LENGTH_SHORT).show()
            }
            procedencia.isEmpty() -> {
                Toast.makeText(this, "Ingresa la procedencia del artículo", Toast.LENGTH_SHORT).show()
            }
            !declaracion -> {
                Toast.makeText(
                    this,
                    "Debes aceptar la declaración de legalidad",
                    Toast.LENGTH_SHORT
                ).show()
            }
            !verificarInternet() -> {
                Toast.makeText(this, "Error: Revisa tu conexión a internet", Toast.LENGTH_LONG)
                    .show()
            }
            else -> {
                // TODO: Enviar datos a servidor
                Toast.makeText(this, "Solicitud enviada correctamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verificarInternet(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork
        val caps = cm.getNetworkCapabilities(network)
        return caps?.let {
            it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } ?: false
    }
}