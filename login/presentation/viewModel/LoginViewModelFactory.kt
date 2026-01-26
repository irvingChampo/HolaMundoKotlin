package com.example.holamundo.login.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.holamundo.login.domain.usecase.LoginUseCase
import com.example.holamundo.login.domain.usecase.RegisterUseCase

class LoginViewModelFactory(
    private val loginUseCase : LoginUseCase,
    private val registerUseCase : RegisterUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginUseCase, registerUseCase) as T
    }

}