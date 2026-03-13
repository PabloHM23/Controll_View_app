package com.example.controllview.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextFieldDefaults.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.controllview.ui.theme.*

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }

    var pass by remember { mutableStateOf("") }

    fun IsEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    var mailError = remember { mutableStateOf(false) }
    var passError = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize().background(White).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextDark,
            modifier = Modifier.padding(top = 40.dp))

        Spacer(modifier = Modifier.height(48.dp))

        CustomTextField(
            value = email,
            onValueChange = {
                email = it },
            label = "Email Address",
            IsError = mailError,
            errorMessage = "Invalid Email",
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Purple,
                unfocusedIndicatorColor = LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = pass,
            onValueChange = { pass = it },
            label = "Password",
            isPassword = true,
            IsError = passError,
            errorMessage = "Invalid Password",
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Purple,
                unfocusedIndicatorColor = LightGray
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Acción */ },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.weight(1f))
        Text("Don't have an account? Sign Up",
            modifier = Modifier.clickable { navController.navigate("signup") }, color = Purple)
    }
}

@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var mailError = remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    fun IsEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPhoneValid(phone: String): Boolean {
        return android.util.Patterns.PHONE.matcher(phone).matches()
    }


    Column(
        modifier = Modifier.fillMaxSize().background(White).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create Account", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextDark,
            modifier = Modifier.padding(top = 40.dp))

        Spacer(modifier = Modifier.height(48.dp))
        CustomTextField(
            value = name,
            onValueChange = { name = it },
            label = "Full Name",
            IsError = mailError,
            errorMessage = "Invalid Name",
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Purple,
                unfocusedIndicatorColor = LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            IsError = mailError,
            errorMessage = "Invalid Email",
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Purple,
                unfocusedIndicatorColor = LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = phone,
            onValueChange = {
                phone = it
                var mailError = !IsEmailValid(email = it)},
            label = "Phone Number",
            IsError = mailError,
            errorMessage = "Invalid Phone Number",
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Purple,
                unfocusedIndicatorColor = LightGray
            )
        )


        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = pass,
            onValueChange = { pass = it },
            label = "Password",
            isPassword = true,
            IsError = mailError,
            errorMessage = "Invalid Password",
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Purple,
                unfocusedIndicatorColor = LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = pass,
            onValueChange = { pass = it },
            label = "Confirm Password",
            isPassword = true,
            IsError = mailError,
            errorMessage = "Password Error",
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Purple,
                unfocusedIndicatorColor = LightGray
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { /* Acción */ },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Create Account")
        }

        Spacer(modifier = Modifier.weight(1f))
        Text("Already have an account? Login",
            modifier = Modifier.clickable { navController.popBackStack() }, color = Purple)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    IsError: MutableState<Boolean>,
    errorMessage: String,
    colors: TextFieldColors
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        colors = colors(
            focusedBorderColor = Purple,
            unfocusedBorderColor = LightGray,
            focusedContainerColor = LightGray,
            unfocusedContainerColor = LightGray,
            focusedLabelColor = Purple,
            unfocusedLabelColor = TextGray
        )
    )
}