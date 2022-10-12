package co.edu.udea.compumovil.funcionesmatriciales

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_vista.*
import java.io.File
import java.util.*
import org.apache.commons.math3.fraction.BigFraction;


class MainActivity : AppCompatActivity() {
    public var Resultado: ArrayList<Producto>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val MU:Button=findViewById(R.id.MatrizU)
        val SEL:Button=findViewById(R.id.SEcLineal)
        val MxM:Button=findViewById(R.id.MatrizxMatriz)
        val MxE:Button=findViewById(R.id.MatrizxEscalar)
        val D:Button=findViewById(R.id.Determinante)





        MU.setOnClickListener{
            borrar()
            val MatrizCuadrada=MatrizOrignal()
            val v=MatrizU(MatrizCuadrada)
            Resultado=Mostrar(v)


            val intent: Intent = Intent(this,VistaActivity::class.java)
            startActivity(intent)

        }
        SEL.setOnClickListener{
            borrar()
            val MatrizCuadrada=MatrizOrignal()
            val v=EcLineal(MatrizCuadrada)
            Resultado=Mostrar(v)
            val intent: Intent = Intent(this,VistaActivity::class.java)
            startActivity(intent)

        }
        MxM.setOnClickListener{
            val MatrizCuadrada=MatrizOrignal()
            borrar()
            val v=MultiplicaMatrizxMatriz(MatrizCuadrada,MatrizCuadrada)
            Resultado=Mostrar(v)
            val intent: Intent = Intent(this,VistaActivity::class.java)
            startActivity(intent)

        }
        MxE.setOnClickListener{
            val MatrizCuadrada=MatrizOrignal()
            borrar()
            val v=MultiplicaMatrizxEscalar(MatrizCuadrada,3f)
            Resultado=Mostrar(v)
            val intent: Intent = Intent(this,VistaActivity::class.java)
            startActivity(intent)

        }
        D.setOnClickListener{
            val MatrizCuadrada=MatrizOrignal()
            borrar()
            val v=Determinante(MatrizCuadrada)
        }

/*
        Mostrar(MatrizCuadrada)

        val MatrizCuadrada1 = OrganizaPibote(MatrizCuadrada)//FUNCIONA PERFECTAMENTE
        Mostrar(MatrizCuadrada1)


        var MatrizCuadradaU = MatrizU(MatrizCuadrada1)
        println("\n Matriz U \n")
        Mostrar(MatrizCuadradaU)


        //var MatrizCuadradaEc = EcLineal(MatrizCuadrada1)

        //println("\n Matriz Ec. Lineales\n")
        //Mostrar(MatrizCuadradaEc)

        println("\n Determinante \n")

        Determinante(MatrizCuadrada1)


        MultiplicaMatrizxMatriz(MatrizCuadrada,MatrizCuadrada)

       val a=MultiplicaMatrizxEscalar(MatrizCuadrada,3.5f)
        Mostrar(a)

*/

    }



    fun Determinante(MatrizCuadrada: ArrayList<FloatArray>){

        val MatrizTriangular=MatrizU(MatrizCuadrada)
        var Det=1f
        for(i in MatrizTriangular.indices){
            print("(${MatrizTriangular[i][i]})")
            Det*=MatrizTriangular[i][i]
        }
        println("\nEl Determinante de la matriz es: ${Det}")
    }

    fun EcLineal(MatrizCuadradaE: ArrayList<FloatArray>): ArrayList<FloatArray> {
        var MatrizCuadradaEp = MatrizU(MatrizCuadradaE)

        //Normaliz ultima fila
        var Arreglo1 = floatArrayOf()
        var normal = 0f
        for (l in MatrizCuadradaEp[MatrizCuadradaEp.size-1]) {
            if (l != 0f) {
                normal = l
                break
            }
        }
        for (m in MatrizCuadradaEp[MatrizCuadradaEp.size-1]) {
            Arreglo1 = addElement(Arreglo1, (m / normal))}
        MatrizCuadradaEp.set(MatrizCuadradaEp.size-1, Arreglo1)


        for (i in MatrizCuadradaEp.size - 1 downTo 1) {// i tomara los valores de n-1(fila i) hasta 0
            val pibote = MatrizCuadradaEp[i][i]

            for (j in (i - 1) downTo 0) {

                //capturo valor j(fila baja)
                var t = MatrizCuadradaEp[j][i]
                t = t * -1

                var Arreglo = floatArrayOf()
                for (k in 0..MatrizCuadradaEp[i].size - 1) {
                    var Pos: Float =
                        ((MatrizCuadradaEp[i][k] * t / pibote) + (MatrizCuadradaEp[j][k])).toFloat()
                    Arreglo = addElement(Arreglo, Pos)
                }
                var Arreglo1 = floatArrayOf()
                var normal = 0f
                for (l in Arreglo) {
                    if (l != 0f) {
                        normal = l
                        break
                    }
                }
                for (m in Arreglo) {
                    Arreglo1 = addElement(Arreglo1, (m / normal))
                }
                MatrizCuadradaEp.set(j, Arreglo1)
            }


        }
        return MatrizCuadradaEp


    }

    fun MatrizU(MatrizCuadrada: ArrayList<FloatArray>): ArrayList<FloatArray> {
        var MatrizCuadradaU=MatrizCuadrada
        for (i in MatrizCuadradaU.indices) {// i tomara los valores de 0 hasta n-1(fila i)
            val pibote = MatrizCuadradaU[i][i]
            for (j in i + 1..MatrizCuadradaU.size - 1) {
                //capturo valor j(fila baja)
                var t = MatrizCuadradaU[j][i]
                t = t * -1
                var Arreglo = floatArrayOf()
                for (k in 0..MatrizCuadradaU[i].size - 1) {
                    var Pos: Float =
                        ((MatrizCuadradaU[i][k] * t / pibote) + (MatrizCuadradaU[j][k])).toFloat()
                    Arreglo = addElement(Arreglo, Pos)
                }
                MatrizCuadradaU.set(j, Arreglo)
            }


        }
        return MatrizCuadradaU


    }

    fun OrganizaPibote(MatrizCuadrada1: ArrayList<FloatArray>): ArrayList<FloatArray> {
        var a = true
        while (a) {
            a = false
            for (i in MatrizCuadrada1.indices) {

                if (MatrizCuadrada1[i][i] == 0f) {
                    if (i != (MatrizCuadrada1.size) - 1) {
                        Collections.swap(MatrizCuadrada1, i, i + 1)
                        a = true
                    } else {
                        Collections.swap(MatrizCuadrada1, i, i - 1)
                        a = true
                    }
                }
            }

        }


        return MatrizCuadrada1

    }

    fun addElement(arr: FloatArray, element: Float): FloatArray {
        val mutableArray = arr.toMutableList()
        mutableArray.add(element)
        return mutableArray.toFloatArray()
    }

    fun addElementString(arr: Array<Producto>, element: Producto): Array<Producto> {
        val mutableArray = arr.toMutableList()
        mutableArray.add(element)
        return mutableArray.toTypedArray()
    }



    fun MultiplicaMatrizxEscalar(MatrizCuadradaM: ArrayList<FloatArray>, escalar:Float): ArrayList<FloatArray> {
        for(i in MatrizCuadradaM.indices){
            var Arreglo = floatArrayOf()
            for(j in MatrizCuadradaM[i].indices){
                //println(MatrizCuadradaM[i][j])
                var Pos:Float=escalar*MatrizCuadradaM[i][j]
                Arreglo = addElement(Arreglo, Pos)
            }
            MatrizCuadradaM.set(i, Arreglo)
        }
        return MatrizCuadradaM

    }

    fun EvaluaMultiplicacion(MatrizX: ArrayList<FloatArray>,MatrizY: ArrayList<FloatArray>): Boolean {
        val filasA=MatrizX.size
        val ColumasB=MatrizY[0].size
        println(filasA)
        println(ColumasB)
        if(filasA==ColumasB){
            return true
        }
        else{
            return false}
    }

    fun MultiplicaMatrizxMatriz(MatrizX: ArrayList<FloatArray>,MatrizY: ArrayList<FloatArray>): ArrayList<FloatArray> {
        if(EvaluaMultiplicacion(MatrizX,MatrizY)){
            var MatrizResultado = ArrayList<FloatArray>()
            for (i in MatrizX.indices) {
                var filaRes = floatArrayOf()
                for (j in MatrizY.indices) {
                    var P:Float=0f
                    for(k in MatrizY[j].indices){
                        P+=MatrizX[i][k]*MatrizY[k][j]
                    }
                    filaRes = addElement(filaRes, P)

                }
                MatrizResultado.add(filaRes)
            }
            return(MatrizResultado)
        }
        else{
            return (ArrayList<FloatArray>())
            println("El Tamaño/orden de las matrices es incopatible")
        }
    }

    fun Mostrar(MatrizCuadradaM: ArrayList<FloatArray>): ArrayList<Producto> {
        var listap= ArrayList<Producto>()
        for (j in MatrizCuadradaM.indices) {
            var linea="\n|"
            //println(" | ")
            for (i in MatrizCuadradaM[j].indices) {


                //cont=MatrizCuadradaM[j][i] cont tiene los decimasles y los convierto a fraccion aqui
                var contador=MatrizCuadradaM[j][i].toDouble()
                contador= Math.round(contador*1000)/1000.toDouble()//maximo 3 decimales
                //System.out.printf("%s%n", BigFraction(contador, 0.00000002, 10000))
                var strin=BigFraction(contador, 0.00000002, 10000)
                //println(strin)

                var cont=strin.toString()
                /*if(cont.length>=6){
                    cont= cont.subSequence(0, 6) as String
                }
                else{
                    val i=6-cont.length
                    cont=" ".repeat(i/2)+cont+" ".repeat(i/2)
                }*/
                linea=linea+cont+"|"


            }
            println(linea)
            addMatriz(linea)
            val producto=Producto(linea)
            //listap=addElementString(listap,producto)
            listap.add(producto)


        }



        return listap

    }

    fun addMatriz(linea:String){

        try {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val miCarpeta = File(rutaSD, "Matriz")
            if (!miCarpeta.exists()) {
                miCarpeta.mkdir()
            }
            val ficheroFisico = File(miCarpeta, "Matriz.txt")

            ficheroFisico.appendText(linea)

            //CADA MES DEBE DISMINUIR EL VALOR DE LA VARIABLE MES EN 1 UNIDAD
        }
        catch (e: Exception) {
            Toast.makeText(this,
                "No se ha podido guardar",
                Toast.LENGTH_LONG).show()
        }

    }

    fun borrar(){
        val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
        val miCarpeta = File(rutaSD, "Matriz")
        if (!miCarpeta.exists()) {
            miCarpeta.mkdir()
        }
        val ficheroFisico = File(miCarpeta, "Matriz.txt").delete()
    }

    fun MatrizOrignal(): ArrayList<FloatArray> {
        var MatrizCuadrada = ArrayList<FloatArray>()
        MatrizCuadrada.add(floatArrayOf(3f, 6f, -2f, 9f))
        MatrizCuadrada.add(floatArrayOf(-5f, 4f, 5f, -6f))
        MatrizCuadrada.add(floatArrayOf(-3f, 8f, 2f, -3f))
        MatrizCuadrada.add(floatArrayOf(-4f, 10f, 3f, 9f))

        return MatrizCuadrada

    }



}
