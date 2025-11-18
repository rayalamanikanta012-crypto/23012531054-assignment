package com.example.a23012531054_assignment

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var currentInput = ""
    private var lastOperator: String? = null
    private var firstNumber: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.editTextResult)

        // Set listeners for number buttons
        findViewById<Button>(R.id.button0).setOnClickListener { appendNumber("0") }
        findViewById<Button>(R.id.button1).setOnClickListener { appendNumber("1") }
        findViewById<Button>(R.id.button2).setOnClickListener { appendNumber("2") }
        findViewById<Button>(R.id.button3).setOnClickListener { appendNumber("3") }
        findViewById<Button>(R.id.button4).setOnClickListener { appendNumber("4") }
        findViewById<Button>(R.id.button5).setOnClickListener { appendNumber("5") }
        findViewById<Button>(R.id.button6).setOnClickListener { appendNumber("6") }
        findViewById<Button>(R.id.button7).setOnClickListener { appendNumber("7") }
        findViewById<Button>(R.id.button8).setOnClickListener { appendNumber("8") }
        findViewById<Button>(R.id.button9).setOnClickListener { appendNumber("9") }

        // Set listeners for operator buttons
        findViewById<Button>(R.id.buttonAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.buttonSubtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { setOperator("×") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { setOperator("÷") }

        // Set listeners for action buttons
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.buttonClear).setOnClickListener { clear() }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        resultText.text = currentInput
    }

    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toDouble()
            currentInput = ""
            lastOperator = operator
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && lastOperator != null) {
            val secondNumber = currentInput.toDouble()
            val result = when (lastOperator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "×" -> firstNumber * secondNumber
                "÷" -> if (secondNumber != 0.0) firstNumber / secondNumber else Double.NaN
                else -> 0.0
            }

            if (result.isNaN()) {
                resultText.text = "Error"
            } else {
                resultText.text = result.toString()
            }

            currentInput = result.toString()
            lastOperator = null
        }
    }

    private fun clear() {
        currentInput = ""
        firstNumber = 0.0
        lastOperator = null
        resultText.text = "0"
    }
}
