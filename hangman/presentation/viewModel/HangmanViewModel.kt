package com.example.holamundo.hangman.presentation.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HangmanViewModel : ViewModel() {

    private val words = listOf("LAPTOP", "TELEFONO", "GAMER", "STREAM", "YOUTUBE")

    private val _wordToGuess = MutableStateFlow("")
    val wordToGuess: StateFlow<String> = _wordToGuess.asStateFlow()

    private val _guessedLetters = MutableStateFlow(setOf<Char>())
    val guessedLetters: StateFlow<Set<Char>> = _guessedLetters.asStateFlow()

    private val _attemptsLeft = MutableStateFlow(7)
    val attemptsLeft: StateFlow<Int> = _attemptsLeft.asStateFlow()

    private val _gameState = MutableStateFlow("") // "", "Ganaste", "Perdiste"
    val gameState: StateFlow<String> = _gameState.asStateFlow()

    init {
        resetGame()
    }

    fun resetGame() {
        _wordToGuess.value = words.random().uppercase()
        _guessedLetters.value = emptySet()
        _attemptsLeft.value = 7
        _gameState.value = ""
    }

    fun onLetterSelected(letter: Char) {
        // Si el juego terminó o la letra ya se usó, no hacer nada
        if (_gameState.value != "" || _guessedLetters.value.contains(letter)) return

        val currentGuessed = _guessedLetters.value.toMutableSet()
        currentGuessed.add(letter)
        _guessedLetters.value = currentGuessed

        if (!_wordToGuess.value.contains(letter)) {
            _attemptsLeft.value -= 1
        }

        checkGameStatus()
    }

    private fun checkGameStatus() {
        if (_attemptsLeft.value <= 0) {
            _gameState.value = "HAS PERDIDO EL JUEGO"
        } else {
            // Verificar si todas las letras de la palabra están en las adivinadas
            val isWon = _wordToGuess.value.all { _guessedLetters.value.contains(it) }
            if (isWon) {
                _gameState.value = "¡FELICIDADES, GANASTE!"
            }
        }
    }
}