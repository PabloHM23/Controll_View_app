package com.example.controllview.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.controllview.ui.theme.*

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passError by remember { mutableStateOf(false) }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

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
                email = it
                emailError = email.isNotEmpty() && !isEmailValid(it)
            },
            label = "Email Address",
            isError = emailError,
            errorMessage = "Invalid Email"
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = pass,
            onValueChange = {
                pass = it
                passError = pass.isNotEmpty() && !isPasswordValid(it)
            },
            label = "Password",
            isPassword = true,
            isError = passError,
            errorMessage = "Invalid Password (min 8 chars)"
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Acción de Login */ },
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
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var repass by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var passError by remember { mutableStateOf(false) }
    var repassError by remember { mutableStateOf(false) }

    fun isNameValid(name: String): Boolean = name.isNotEmpty() && name.none { it.isDigit() }
    fun isEmailValid(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isPhoneValid(phone: String): Boolean = android.util.Patterns.PHONE.matcher(phone).matches()
    fun isPasswordValid(password: String): Boolean = password.length >= 8

    Column(
        modifier = Modifier.fillMaxSize().background(White).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create Account", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = TextDark,
            modifier = Modifier.padding(top = 40.dp))

        Spacer(modifier = Modifier.height(48.dp))
        CustomTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = !isNameValid(it)
            },
            label = "Full Name",
            isError = nameError,
            errorMessage = if (name.isEmpty()) "Name cannot be empty" else "Name cannot contain numbers"
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = email.isNotEmpty() && !isEmailValid(it)
            },
            label = "Email",
            isError = emailError,
            errorMessage = "Invalid Email"
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = phone,
            onValueChange = {
                phone = it
                phoneError = phone.isNotEmpty() && !isPhoneValid(it)
            },
            label = "Phone Number",
            isError = phoneError,
            errorMessage = "Invalid Phone Number"
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = pass,
            onValueChange = {
                pass = it
                passError = pass.isNotEmpty() && !isPasswordValid(it)
                repassError = repass.isNotEmpty() && it != repass
            },
            label = "Password",
            isPassword = true,
            isError = passError,
            errorMessage = "Invalid Password (min 8 chars)"
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = repass,
            onValueChange = {
                repass = it
                repassError = it != pass
            },
            label = "Confirm Password",
            isPassword = true,
            isError = repassError,
            errorMessage = "Passwords do not match"
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { /* Acción de Registro */ },
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

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    isError: Boolean,
    errorMessage: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = errorMessage)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Purple,
            unfocusedBorderColor = LightGray,
            focusedContainerColor = LightGray,
            unfocusedContainerColor = LightGray,
            focusedLabelColor = Purple,
            unfocusedLabelColor = TextGray,
            errorBorderColor = Red,
            errorLabelColor = Red,
            errorSupportingTextColor = Red
        )
    )
}
