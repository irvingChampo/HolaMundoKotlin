package com.example.holamundo.login.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.holamundo.login.domain.usecase.LoginUseCase
import com.example.holamundo.login.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(LoginUseCase : LoginUseCase, RegisterUseCase : RegisterUseCase) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _loginMessage = MutableStateFlow("")
    val loginMessage: StateFlow<String> = _loginMessage.asStateFlow()

    private val VALID_USER = "irving"
    private val VALID_PASS = "123456"

    fun onUsernameChange(newValue: String) {
        _username.value = newValue
        _loginMessage.value = ""
    }

    fun onPasswordChange(newValue: String) {
        _password.value = newValue
        _loginMessage.value = ""
    }

    fun onLoginClick() {
        if (_username.value == VALID_USER && _password.value == VALID_PASS) {
            _loginMessage.value = "¡Bienvenido Irving! Acceso concedido."
        } else {
            _loginMessage.value = "Usuario o contraseña incorrectos."
        }
    }
}