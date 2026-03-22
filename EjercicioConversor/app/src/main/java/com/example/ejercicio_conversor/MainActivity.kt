package com.example.ejercicio_conversor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var editTextMonto: EditText
    lateinit var spinnerOrigen: Spinner
    lateinit var spinnerDestino: Spinner
    lateinit var btnConvertir: Button
    lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextMonto = findViewById(R.id.editTextMonto)
        spinnerOrigen = findViewById(R.id.spinnerOrigen)
        spinnerDestino = findViewById(R.id.spinnerDestino)
        btnConvertir = findViewById(R.id.btnConvertir)
        textViewResultado = findViewById(R.id.textViewConversion)

        val monedas = arrayOf("USD", "PEN", "EUR", "GBP", "INR", "BRL", "MXN", "CNY", "JPY")

        val adapter = ArrayAdapter(this, R.layout.spinner_item, monedas)
        adapter.setDropDownViewResource(R.layout.spinner_item)

        spinnerOrigen.adapter = adapter
        spinnerDestino.adapter = adapter

        btnConvertir.setOnClickListener {

            val montoTexto = editTextMonto.text.toString()

            if (montoTexto.isEmpty()) {
                Toast.makeText(this, "Ingrese un monto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val monto = montoTexto.toDouble()
            val origen = spinnerOrigen.selectedItem.toString()
            val destino = spinnerDestino.selectedItem.toString()

            val resultado = convertir(origen, destino, monto)

            textViewResultado.text = "💰 %.2f %s".format(resultado, destino)
        }
    }

    private fun convertir(origen: String, destino: String, monto: Double): Double {

        val tasasUSD = mapOf(
            "USD" to 1.0,
            "PEN" to 3.8,
            "EUR" to 0.92,
            "GBP" to 0.79,
            "INR" to 83.0,
            "BRL" to 5.0,
            "MXN" to 17.0,
            "CNY" to 7.2,
            "JPY" to 150.0
        )

        val montoEnUSD = monto / tasasUSD[origen]!!
        return montoEnUSD * tasasUSD[destino]!!
    }
}