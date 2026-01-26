package com.example.holamundo.hangman.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.holamundo.hangman.presentation.viewModel.HangmanViewModel

@Composable
fun HangmanScreen(viewModel: HangmanViewModel = viewModel()) {
    val wordToGuess by viewModel.wordToGuess.collectAsStateWithLifecycle()
    val guessedLetters by viewModel.guessedLetters.collectAsStateWithLifecycle()
    val attemptsLeft by viewModel.attemptsLeft.collectAsStateWithLifecycle()
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()

    val alphabet = ('A'..'Z').toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Safe Area
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary
        ) {
            Text(
                text = "JUEGO DE AHORCADO",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(16.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Dibujo del muñeco
            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                HangmanDrawing(attemptsLeft)
            }

            // 3. Intentos y Palabra
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Intentos restantes: $attemptsLeft", fontSize = 18.sp)

                Spacer(modifier = Modifier.height(40.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    wordToGuess.forEach { char ->
                        val charDisplay = if (guessedLetters.contains(char) || gameState.contains("PERDIDO")) char.toString() else " "
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = charDisplay, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                            Box(modifier = Modifier.width(30.dp).height(2.dp).background(Color.Black))
                        }
                    }
                }

                if (gameState.isNotEmpty()) {
                    Text(
                        text = gameState,
                        color = if (gameState.contains("GANASTE")) Color(0xFF4CAF50) else Color.Red,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Button(onClick = { viewModel.resetGame() }, modifier = Modifier.padding(top = 10.dp)) {
                        Text("Reiniciar")
                    }
                }
            }

            // Teclado
            Box(modifier = Modifier.weight(1.2f)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(alphabet) { letter ->
                        val isUsed = guessedLetters.contains(letter)
                        Button(
                            onClick = { viewModel.onLetterSelected(letter) },
                            enabled = !isUsed && gameState == "",
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.height(45.dp)
                        ) {
                            Text(text = letter.toString(), fontSize = 16.sp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.navigationBarsPadding().height(10.dp))
        }
    }
}

@Composable
fun HangmanDrawing(attempts: Int) {
    val errorColor = Color.Red
    val defaultColor = Color(0xFFADADAD)
    val sizePart = 50.dp

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Cabeza - Error 1
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(if (attempts <= 5) errorColor else defaultColor, CircleShape)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Brazos y Pecho - Error 2, 3 y 4
        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Box(modifier = Modifier.size(sizePart).background(if (attempts <= 3) errorColor else defaultColor)) // Brazo I
            Box(modifier = Modifier.size(sizePart).background(if (attempts <= 4) errorColor else defaultColor)) // Pecho
            Box(modifier = Modifier.size(sizePart).background(if (attempts <= 2) errorColor else defaultColor)) // Brazo D
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Abdomen/Cuerpo bajo - Error 5
        Box(modifier = Modifier.size(sizePart).background(if (attempts <= 1) errorColor else defaultColor))

        Spacer(modifier = Modifier.height(15.dp))

        // Piernas - Error 6 (Se pintan las dos o una por una, aquí repartiremos el último error)
        Row(horizontalArrangement = Arrangement.spacedBy(50.dp)) {
            Box(modifier = Modifier.size(sizePart).background(if (attempts <= 0) errorColor else defaultColor)) // Pierna I
            Box(modifier = Modifier.size(sizePart).background(if (attempts <= 0) errorColor else defaultColor)) // Pierna D
        }
    }
}