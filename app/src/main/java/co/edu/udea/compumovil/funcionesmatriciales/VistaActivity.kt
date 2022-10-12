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
        var max=0
        val t= texto.split("\n").toMutableList()
        for(pos in t){
            var pos=pos.split("|")
            for(cont in pos){
                if(cont.length>max){
                    max=cont.length
                }
            }
        }
        println("El valor maximo de caracteres es: $max")
        //caracter mas espacioso ej:10
        for(i in t.indices){
            var item= t[i].split("|").toMutableList()
            for(j in 0..item.size-1){
                var k=item[j]
                //println(k.length)
                if(k.length<max){
                    var diferencia=max-k.length
                    if(diferencia%2==0){
                        var R=" ".repeat(diferencia/2)
                        k="$R$k$R"
                    }
                    else{
                        var R=" ".repeat(diferencia/2)
                        var R1=" ".repeat((diferencia/2)+1)
                        k="$R$k$R1"
                    }
                }
                println(k)
                item[j]=k

            }
            t[i]=item.toString()

            t[i]=t[i].replace(Regex("[,]"), "|")
            t[i]=t[i].replace("[","|")
            t[i]=t[i].replace("]","|")

        }



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