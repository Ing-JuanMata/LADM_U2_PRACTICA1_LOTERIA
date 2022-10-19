package mx.edu.ittepic.ladm_u2_practica1_loteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.ittepic.ladm_u2_practica1_loteria.databinding.ActivityMainBinding
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var cartas = arrayOf(
        R.drawable.c1,
        R.drawable.c2,
        R.drawable.c3,
        R.drawable.c4,
        R.drawable.c5,
        R.drawable.c6,
        R.drawable.c7,
        R.drawable.c8,
        R.drawable.c9,
        R.drawable.c10,
        R.drawable.c11,
        R.drawable.c12,
        R.drawable.c13,
        R.drawable.c14,
        R.drawable.c15,
        R.drawable.c16,
        R.drawable.c17,
        R.drawable.c18,
        R.drawable.c19,
        R.drawable.c20,
        R.drawable.c21,
        R.drawable.c22,
        R.drawable.c23,
        R.drawable.c24,
        R.drawable.c25,
        R.drawable.c26,
        R.drawable.c27,
        R.drawable.c28,
        R.drawable.c29,
        R.drawable.c30,
        R.drawable.c31,
        R.drawable.c32,
        R.drawable.c33,
        R.drawable.c34,
        R.drawable.c35,
        R.drawable.c36,
        R.drawable.c37,
        R.drawable.c38,
        R.drawable.c39,
        R.drawable.c40,
        R.drawable.c41,
        R.drawable.c42,
        R.drawable.c43,
        R.drawable.c44,
        R.drawable.c45,
        R.drawable.c46,
        R.drawable.c47,
        R.drawable.c48,
        R.drawable.c49,
        R.drawable.c50,
        R.drawable.c51,
        R.drawable.c52,
        R.drawable.c53,
        R.drawable.c54
    )
    var jugando = false
    private var baraja = Baraja(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnIniciarParar.setOnClickListener {
            if (!jugando && !baraja.isAlive) {
                barajar()
            }
            jugando = !jugando
            binding.revisar.isEnabled = true
        }

        binding.revisar.setOnClickListener {
            if (!baraja.isAlive) return@setOnClickListener
            if (jugando) {
                jugando = false
                binding.revisar.isEnabled = false
                baraja.mostrar = true
                binding.revisar.text = "Continuar"
            } else {
                jugando = true
                binding.revisar.text = "Ver faltantes"
            }
        }
    }

    private fun barajar() = GlobalScope.launch {
        (cartas.indices).forEach {
            val r = it + (Random(Calendar.getInstance().timeInMillis).nextInt(0, 55) % (54 - it))
            val tmp = cartas[it]
            cartas[it] = cartas[r]
            cartas[r] = tmp
        }
        Log.d("barajar", "terminado")
        baraja = Baraja(this@MainActivity)
        baraja.start()
    }
}

class Baraja(puntero: MainActivity) : Thread() {
    private val p = puntero
    var indice = 0
    var mostrar = false

    override fun run() {
        while (indice < p.cartas.size) {
            if (p.jugando && !mostrar) {
                p.runOnUiThread { p.binding.imagen.setImageResource(p.cartas[indice++]) }
                sleep(3200)
            }

            if (mostrar) {
                mostrarFaltantes()
                mostrar = false
                p.runOnUiThread { p.binding.revisar.isEnabled = true }
            }
        }
    }

    private fun mostrarFaltantes() {
        indice--
        val i = indice
        (i until p.cartas.size).forEach {
            sleep(500)
            p.runOnUiThread { p.binding.imagen.setImageResource(p.cartas[it]) }
        }
    }
}