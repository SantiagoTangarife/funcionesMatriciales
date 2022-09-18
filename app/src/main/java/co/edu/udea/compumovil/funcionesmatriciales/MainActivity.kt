package co.edu.udea.compumovil.funcionesmatriciales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fila.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val producto=Producto("0")
        val producto2=Producto("2")
        val listap= listOf(producto,producto2)
        val adapter=ProductoAdapter(this,listap)
        lista.adapter=adapter
        //setContentView(R.layout.fila)





        var MatrizCuadrada = ArrayList<FloatArray>()
        MatrizCuadrada.add(floatArrayOf(3f, 6f, -2f, 9f))
        MatrizCuadrada.add(floatArrayOf(-5f, 4f, 5f, -6f))
        MatrizCuadrada.add(floatArrayOf(-3f, 8f, 2f, -3f))
        MatrizCuadrada.add(floatArrayOf(-4f, 10f, 3f, 9f))

        Mostrar(MatrizCuadrada)

        val MatrizCuadrada1 = OrganizaPibote(MatrizCuadrada)//FUNCIONA PERFECTAMENTE
        Mostrar(MatrizCuadrada1)


        var MatrizCuadradaU = MatrizU(MatrizCuadrada1)
        println("\n Matriz U \n")
        Mostrar(MatrizCuadradaU)
/*

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
        Mostrar(MatrizTriangular)
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

    fun Mostrar(MatrizCuadradaM: ArrayList<FloatArray>) {
        for (j in MatrizCuadradaM.indices) {
            println()
            for (i in MatrizCuadradaM[j].indices) {
                print(MatrizCuadradaM[j][i])
                print(" ")
            }
        }
        println("\n--------------------------\n")
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

    fun MultiplicaMatrizxMatriz(MatrizX: ArrayList<FloatArray>,MatrizY: ArrayList<FloatArray>){
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
            Mostrar(MatrizResultado)
        }
        else{
            println("El Tamaño/orden de las matrices es incopatible")
        }
    }
}
