package com.example.holamundo.login.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.holamundo.R
import com.example.holamundo.login.domain.usecase.LoginUseCase
import com.example.holamundo.login.domain.usecase.RegisterUseCase
import com.example.holamundo.login.presentation.viewModel.LoginViewModel
import com.example.holamundo.login.presentation.viewModel.LoginViewModelFactory

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(
    factory = LoginViewModelFactory(
        LoginUseCase(),
        RegisterUseCase()
    )
)) {
    val username by viewModel.username.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val loginMessage by viewModel.loginMessage.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Bienvenido",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.aws),
                contentDescription = "Logo empresa",
                modifier = Modifier.size(size = 180.dp)
            )

            Spacer(modifier = Modifier.height(height = 40.dp))

            TextField(
                value = username,
                onValueChange = { viewModel.onUsernameChange(it) },
                placeholder = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 5.dp),
                singleLine = true
            )

            TextField(
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = { Text(text = "Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 5.dp),
                visualTransformation = PasswordVisualTransformation(), // Oculta la clave
                singleLine = true
            )

            if (loginMessage.isNotEmpty()) {
                Text(
                    text = loginMessage,
                    color = if (loginMessage.contains("Bienvenido")) Color(0xFF4CAF50) else Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { viewModel.onLoginClick() },
                modifier = Modifier.height(50.dp).width(200.dp)
            ) {
                Text(text = "Iniciar sesion")
            }
        }

        Text(
            text = "@champo",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}