package com.example.holamundo.calculator.presentation.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculatorViewModel : ViewModel() {

    private val _displayText = MutableStateFlow("0")
    val displayText: StateFlow<String> = _displayText

    private var firstNumber = 0.0
    private var currentOperator = ""
    private var isNewOp = true

    fun onDigitClick(digit: String) {
        if (isNewOp) {
            _displayText.value = digit
            isNewOp = false
        } else {
            if (_displayText.value == "0") {
                _displayText.value = digit
            } else {
                _displayText.value += digit
            }
        }
    }

    fun onOperatorClick(op: String) {
        firstNumber = _displayText.value.toDoubleOrNull() ?: 0.0
        currentOperator = op
        isNewOp = true
    }

    fun onEqualClick() {
        val secondNumber = _displayText.value.toDoubleOrNull() ?: 0.0
        val result = when (currentOperator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else "Error"
            else -> secondNumber
        }
        _displayText.value = result.toString()
        isNewOp = true
    }

    fun onClearClick() {
        _displayText.value = "0"
        firstNumber = 0.0
        currentOperator = ""
        isNewOp = true
    }
}