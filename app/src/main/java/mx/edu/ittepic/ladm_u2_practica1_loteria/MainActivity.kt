package mx.edu.ittepic.ladm_u2_practica1_loteria

import android.media.MediaPlayer
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
                binding.btnIniciarParar.text = "Pausar"
            }
            jugando = !jugando
            if (jugando) binding.btnIniciarParar.text = "Pausar"
            else binding.btnIniciarParar.text = "Iniciar"

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
        baraja = Baraja(this@MainActivity)
        baraja.start()
    }
}

class Baraja(puntero: MainActivity) : Thread() {
    private val p = puntero
    var indice = 0
    var mostrar = false
    private var mediaPlayer: MediaPlayer = MediaPlayer()

    override fun run() {
        while (indice < p.cartas.size) {
            if (p.jugando && !mostrar) {
                p.runOnUiThread { p.binding.imagen.setImageResource(p.cartas[indice++]) }
                sleep(100)
                reproducir(p.cartas[indice - 1])
                sleep(4000)
            }

            if (mostrar) {
                mostrarFaltantes()
                mostrar = false
                p.runOnUiThread { p.binding.revisar.isEnabled = true }
            }
        }
        p.jugando = false
    }

    private fun mostrarFaltantes() {
        indice--
        val i = indice
        (i until p.cartas.size).forEach {
            sleep(1000)
            p.runOnUiThread { p.binding.imagen.setImageResource(p.cartas[it]) }
        }
    }

    private fun reproducir(carta: Int) {
        mediaPlayer.stop()
        mediaPlayer.release()
        when (carta) {
            R.drawable.c1 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.gallo)
                mediaPlayer.start()
            }
            R.drawable.c2 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.diablito)
                mediaPlayer.start()
            }
            R.drawable.c3 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.dama)
                mediaPlayer.start()
            }
            R.drawable.c4 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.catrin)
                mediaPlayer.start()
            }
            R.drawable.c5 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.paraguas)
                mediaPlayer.start()
            }
            R.drawable.c6 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.sirena)
                mediaPlayer.start()
            }
            R.drawable.c7 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.escalera)
                mediaPlayer.start()
            }
            R.drawable.c8 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.botella)
                mediaPlayer.start()
            }
            R.drawable.c9 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.barril)
                mediaPlayer.start()
            }
            R.drawable.c10 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.arbol)
                mediaPlayer.start()
            }
            R.drawable.c11 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.melon)
                mediaPlayer.start()
            }
            R.drawable.c12 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.valiente)
                mediaPlayer.start()
            }
            R.drawable.c13 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.gorrito)
                mediaPlayer.start()
            }
            R.drawable.c14 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.muerte)
                mediaPlayer.start()
            }
            R.drawable.c15 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.pera)
                mediaPlayer.start()
            }
            R.drawable.c16 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.bandera)
                mediaPlayer.start()
            }
            R.drawable.c17 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.bandolon)
                mediaPlayer.start()
            }
            R.drawable.c18 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.violoncello)
                mediaPlayer.start()
            }
            R.drawable.c19 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.garza)
                mediaPlayer.start()
            }
            R.drawable.c20 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.pajaro)
                mediaPlayer.start()
            }
            R.drawable.c21 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.mano)
                mediaPlayer.start()
            }
            R.drawable.c22 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.bota)
                mediaPlayer.start()
            }
            R.drawable.c23 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.luna)
                mediaPlayer.start()
            }
            R.drawable.c24 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.cotorro)
                mediaPlayer.start()
            }
            R.drawable.c25 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.borracho)
                mediaPlayer.start()
            }
            R.drawable.c26 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.negrito)
                mediaPlayer.start()
            }
            R.drawable.c27 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.corazon)
                mediaPlayer.start()
            }
            R.drawable.c28 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.sandia)
                mediaPlayer.start()
            }
            R.drawable.c29 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.tambor)
                mediaPlayer.start()
            }
            R.drawable.c30 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.camaron)
                mediaPlayer.start()
            }
            R.drawable.c31 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.jaras)
                mediaPlayer.start()
            }
            R.drawable.c32 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.musico)
                mediaPlayer.start()
            }
            R.drawable.c33 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.arania)
                mediaPlayer.start()
            }
            R.drawable.c34 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.soldado)
                mediaPlayer.start()
            }
            R.drawable.c35 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.estrella)
                mediaPlayer.start()
            }
            R.drawable.c36 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.cazo)
                mediaPlayer.start()
            }
            R.drawable.c37 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.mundo)
                mediaPlayer.start()
            }
            R.drawable.c38 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.apache)
                mediaPlayer.start()
            }
            R.drawable.c39 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.nopal)
                mediaPlayer.start()
            }
            R.drawable.c40 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.alacran)
                mediaPlayer.start()
            }
            R.drawable.c41 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.rosa)
                mediaPlayer.start()
            }
            R.drawable.c42 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.calavera)
                mediaPlayer.start()
            }
            R.drawable.c43 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.campana)
                mediaPlayer.start()
            }
            R.drawable.c44 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.cantarito)
                mediaPlayer.start()
            }
            R.drawable.c45 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.venado)
                mediaPlayer.start()
            }
            R.drawable.c46 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.sol)
                mediaPlayer.start()
            }
            R.drawable.c47 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.corona)
                mediaPlayer.start()
            }
            R.drawable.c48 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.chalupa)
                mediaPlayer.start()
            }
            R.drawable.c49 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.pino)
                mediaPlayer.start()
            }
            R.drawable.c50 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.pescado)
                mediaPlayer.start()
            }
            R.drawable.c51 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.palma)
                mediaPlayer.start()
            }
            R.drawable.c52 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.maceta)
                mediaPlayer.start()
            }
            R.drawable.c53 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.arpa)
                mediaPlayer.start()
            }
            R.drawable.c54 -> {
                mediaPlayer = MediaPlayer.create(p, R.raw.rana)
                mediaPlayer.start()
            }
        }
    }
}