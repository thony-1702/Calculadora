package app.sethtec.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val calculadora = Calculadora()

    private lateinit var tvResultado: TextView
    private lateinit var etFirstNumber: EditText
    private lateinit var etSecondNumber: EditText
    private lateinit var btSuma: Button
    private lateinit var btResta: Button
    private lateinit var btMultiplicacion: Button
    private lateinit var btDivision: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // vistas
        tvResultado = findViewById(R.id.tv_resultado)
        etFirstNumber = findViewById(R.id.first_number)
        etSecondNumber = findViewById(R.id.second_number)
        btSuma = findViewById(R.id.bt_suma)
        btResta = findViewById(R.id.bt_resta)
        btMultiplicacion = findViewById(R.id.bt_multiplicacion)
        btDivision = findViewById(R.id.bt_divicion)

        // eventos
        btSuma.setOnClickListener { operar("suma") }
        btResta.setOnClickListener { operar("resta") }
        btMultiplicacion.setOnClickListener { operar("multiplicacion") }
        btDivision.setOnClickListener { operar("division") }
    }

    private fun operar(operacion: String) {
        val num1 = etFirstNumber.text.toString().toDoubleOrNull()
        val num2 = etSecondNumber.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Por favor ingrese números válidos", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = when (operacion) {
            "suma" -> calculadora.suma(num1, num2)
            "resta" -> calculadora.resta(num1, num2)
            "multiplicacion" -> calculadora.multiplicacion(num1, num2)
            "division" -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                    return
                }
                calculadora.division(num1, num2)
            }
            else -> 0.0
        }

        tvResultado.text = "RESULTADO: ${"%.2f".format(resultado)}"
    }
}
