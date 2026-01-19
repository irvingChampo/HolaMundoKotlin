package com.example.holamundo.login.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.holamundo.R

@Composable
fun LoginScreen(){
    var username by remember {mutableStateOf(value = "")}
    var password = remember {mutableStateOf(value = "")}

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
        Surface() {
            Text(text = "Bienvenido")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.aws),
                contentDescription = "Logo empresa",
                modifier = Modifier.size(size = 200.dp))
            Spacer(modifier = Modifier.height(height = 50.dp))
            TextField(
                value = username,
                onValueChange = {username = it},
                placeholder = {Text(text = "Username")},
                modifier = Modifier.fillMaxWidth()
                    .padding(all = 10.dp)
            )
            TextField(
                value = password.value,
                onValueChange = {password.value = it},
                placeholder = {Text(text = "Password")},
                modifier = Modifier.fillMaxWidth()
                    .padding(all = 10.dp)
            )
            Button(
                onClick = {}
            ) {
                Text(text = "Iniciar sesion")
            }
        }
        Text(text = "@champo")
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}