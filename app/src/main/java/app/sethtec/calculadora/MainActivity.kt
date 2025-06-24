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
    private lateinit var btnIrParImpar: Button
    private lateinit var bt0: Button
    private lateinit var bt1: Button
    private lateinit var bt2: Button
    private lateinit var bt3: Button
    private lateinit var bt4: Button
    private lateinit var bt5: Button
    private lateinit var bt6: Button
    private lateinit var bt7: Button
    private lateinit var bt8: Button
    private lateinit var bt9: Button
    private lateinit var btSuma: Button
    private lateinit var btResta: Button
    private lateinit var btMultiplicacion: Button
    private lateinit var btDivision: Button // Corregido de bt_divicion a btDivision
    private lateinit var btBorrar: Button

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
        btnIrParImpar = findViewById(R.id.btn_ir_par_impar)
        bt0 = findViewById(R.id.bt_0)
        bt1 = findViewById(R.id.bt_1)
        bt2 = findViewById(R.id.bt_2)
        bt3 = findViewById(R.id.bt_3)
        bt4 = findViewById(R.id.bt_4)
        bt5 = findViewById(R.id.bt_5)
        bt6 = findViewById(R.id.bt_6)
        bt7 = findViewById(R.id.bt_7)
        bt8 = findViewById(R.id.bt_8)
        bt9 = findViewById(R.id.bt_9)
        btSuma = findViewById(R.id.bt_suma)
        btResta = findViewById(R.id.bt_resta)
        btMultiplicacion = findViewById(R.id.bt_multiplicacion)
        btDivision = findViewById(R.id.bt_divicion) // Corregido de bt_divicion a btDivision
        btBorrar = findViewById(R.id.bt_borrar)

        // eventos
        // Configurar OnClickListener para el botón btnIrParImpar
        btnIrParImpar.setOnClickListener {
            // Crear una instancia de tu ParImparFragment
            val parImparFragment = ParImparFragment()

            // Obtener el FragmentManager y comenzar una transacción
            supportFragmentManager.beginTransaction().apply {
                // Reemplazar el contenido del contenedor (R.id.main) con el fragment
                // R.id.main es el ID de tu ConstraintLayout raíz en activity_main.xml
                replace(R.id.main, parImparFragment)
                // Opcional: Agregar la transacción al back stack
                // Esto permite al usuario volver a la vista anterior (la calculadora)
                // presionando el botón "Atrás" del dispositivo.
                addToBackStack(null)
                // Confirmar la transacción
                commit()
            }
        }

        // Listeners para los botones numéricos
        val numberButtons = listOf(bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9)
        numberButtons.forEach { button ->
            button.setOnClickListener {
                agregarNumero(button.text.toString())
            }
        }

        // Listeners para los botones de operaciones
        btSuma.setOnClickListener { agregarOperador("+") }
        btResta.setOnClickListener { agregarOperador("-") }
        btMultiplicacion.setOnClickListener { agregarOperador("X") }
        btDivision.setOnClickListener { agregarOperador("/") }

        // Listener para el botón de borrar
        btBorrar.setOnClickListener { borrarUltimo() }

        // Listener para el botón de igual (a implementar si se añade)
        // Ejemplo: findViewById<Button>(R.id.bt_igual).setOnClickListener { calcularResultado() }
    }

    private fun agregarNumero(numero: String) {
        if (tvResultado.text.toString() == "RESULTADO" || tvResultado.text.toString() == "0") {
            tvResultado.text = numero
        } else {
            tvResultado.append(numero)
        }
    }

    private fun agregarOperador(operador: String) {
        val textoActual = tvResultado.text.toString()
        if (textoActual != "RESULTADO" && textoActual.isNotEmpty() && !esOperador(textoActual.last().toString())) {
            tvResultado.append(operador)
        }
    }

    private fun esOperador(char: String): Boolean {
        return char == "+" || char == "-" || char == "X" || char == "/"
    }

    private fun borrarUltimo() {
        val textoActual = tvResultado.text.toString()
        if (textoActual.isNotEmpty() && textoActual != "RESULTADO") {
            tvResultado.text = textoActual.substring(0, textoActual.length - 1)
            if (tvResultado.text.isEmpty()) {
                tvResultado.text = "0"
            }
        }
    }

    // private fun calcularResultado() {
    // Aquí iría la lógica para evaluar la expresión en tvResultado.text
    // Por ejemplo, usando una librería para evaluar expresiones matemáticas
    // o implementando un parser simple.
    // Por ahora, solo mostramos un Toast.
    //     Toast.makeText(this, "Calcular resultado (pendiente)", Toast.LENGTH_SHORT).show()
    //     val expresion = tvResultado.text.toString()
    //     // Lógica de cálculo...
    //     // tvResultado.text = "Resultado calculado"
    // }
}
