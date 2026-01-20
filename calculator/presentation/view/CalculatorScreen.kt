package com.example.holamundo.calculator.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.holamundo.calculator.presentation.viewModel.CalculatorViewModel

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    val displayText by viewModel.displayText.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,


        ) {
            Text("Calculadora de Irving",
                fontSize = 30.sp)
        }
        Box(
            modifier = Modifier.fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(text = displayText, fontSize = 68.sp)
        }

        val buttons = listOf(
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf("C", "0", "=", "+")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { char ->
                    Button(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        onClick = {
                            when (char) {
                                "C" -> viewModel.onClearClick()
                                "=" -> viewModel.onEqualClick()
                                "+", "-", "*", "/" -> viewModel.onOperatorClick(char)
                                else -> viewModel.onDigitClick(char)
                            }
                        }
                    ) {
                        Text(text = char, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}