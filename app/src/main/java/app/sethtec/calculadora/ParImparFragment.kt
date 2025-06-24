package app.sethtec.calculadora

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class ParImparFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_par_impar, container, false)

        val editTextNumber = view.findViewById<EditText>(R.id.editTextNumber)
        val buttonCheck = view.findViewById<Button>(R.id.buttonCheck)
        val textViewResult = view.findViewById<TextView>(R.id.textViewResult)

        buttonCheck.setOnClickListener {
            val numberStr = editTextNumber.text.toString()
            if (numberStr.isNotEmpty()) {
                try {
                    val number = numberStr.toInt()
                    if (number % 2 == 0) {
                        textViewResult.text = "El número $number es PAR"
                    } else {
                        textViewResult.text = "El número $number es IMPAR"
                    }
                } catch (e: NumberFormatException) {
                    textViewResult.text = "Por favor, ingrese un número válido."
                }
            } else {
                textViewResult.text = "Por favor, ingrese un número."
            }
        }

        return view
    }

}