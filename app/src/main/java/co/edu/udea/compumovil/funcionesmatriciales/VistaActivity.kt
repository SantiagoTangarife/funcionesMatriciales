package co.edu.udea.compumovil.funcionesmatriciales

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_vista.*
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

class VistaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista)
        println("*****************************************************************************")


        val lista1= CargarDb()

        val adapter=ProductoAdapter(this,lista1)
        listaM.adapter=adapter
        println("\n--------------------------\n")




    }
    fun CargarDb() : ArrayList<Producto> {
        var texto = ""
        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "Matriz")//CREO ARCHIVO Ruta
            val ficheroFisico = File(miCarpeta, "Matriz.txt")//BUSCO ARCHIVO
            val fichero = BufferedReader(       //Leer archivo
                InputStreamReader(FileInputStream(ficheroFisico))
            )
            texto = fichero.use(BufferedReader::readText)
        }
        catch (e : Exception) {
            // Nada
        }

        val t=texto.split("\n")
        val line= arrayListOf<Producto>()
        for(i in t){
            if(i!=""){
                val producto:Producto;
                producto=Producto(i)
                line.add(producto)}
        }
        return line

    }





}