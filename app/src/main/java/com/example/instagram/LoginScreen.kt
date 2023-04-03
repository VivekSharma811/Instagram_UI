package com.example.instagram

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instagram.ui.theme.ColorBLUE

@Composable
fun LoginScreen(
    navController: NavController
) {
    var email by remember {
        mutableStateOf("")
    }

    var error: String? by remember {
        mutableStateOf(null)
    }

    error?.let {
        ErrorDialog(
            setShowDialog = {
                error = null
            },
            error = it
        )
    }

    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_instagram),
            contentDescription = "Instagram",
            modifier = Modifier.size(100.dp),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.height(100.dp))

        AppTextField(
            hint = "Enter Email Address",
            text = email,
            onValueChange = { email = it },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = ColorBLUE
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        AppTextField(
            hint = "Enter Password",
            text = password,
            onValueChange = { password = it },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = ColorBLUE
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorBLUE
            ),
            onClick = {
                if (email.isEmpty()) {
                    error = "Email cant be empty"
                } else if (password.isEmpty()) {
                    error = "Password cant be empty"
                } else {
                    navController.navigate(Screens.HOME) {
                        popUpTo(Screens.Login) {
                            inclusive = true
                        }
                    }
                }
            }) {
            Text(
                text = "Log in",
                color = Color.White,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        val annotatedString = buildAnnotatedString {
            append("Don’t have an account? ")
            withStyle(style = SpanStyle(color = ColorBLUE)) {
                append("Sign up")
            }
        }

        Text(text = annotatedString,
            modifier = Modifier.clickable { navController.navigate(Screens.SIGN_UP) })

        Spacer(modifier = Modifier.height(20.dp))
    }
}