package com.yunwah.movieapp.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LoginScreen(
    event: (LoginEvent) -> Unit,
    navController: NavHostController,
    viewModel: LoginViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val annotatedString = buildAnnotatedString {
        append("Don't have an account? ")
        pushStringAnnotation("Link", "SignUp")
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.SemiBold)) {
            append("Sign up")
        }
        pop()
    }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                modifier = Modifier.statusBarsPadding(),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                ),
                title = {
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Blue)
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Welcome back \uD83D\uDC4B",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    ),
                    color = Color.Blue
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "I am so happy to see you again. You can continue to login for more features.",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    label = { Text("Username", color = Color.Gray) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        // handle next action
                    }),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.LightGray.copy(.3f),
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    ),
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    label = { Text("Password", color = Color.Gray) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardActions = KeyboardActions(onDone = {
                        // handle done action
                    }),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.LightGray.copy(.3f),
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    ),
                )
            }
            Column(modifier = Modifier.navigationBarsPadding()) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(size = 12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    onClick = {
                        val pResult = viewModel.checkPassword(password, viewModel.hashedPassword)
                        val uResult1 = viewModel.checkUsername(username)
                        if (pResult && uResult1) {
                            event(LoginEvent.SaveAppEntry)
                        } else {
                            showDialog = true
                        }
                    },
                    enabled = username.isNotBlank() && password.isNotBlank()
                ) {
                    Text(text = "Login")
                }
                ClickableText(
                    text = annotatedString,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    onClick = { offset ->
                        // Handle click event
                        val annotations = annotatedString.getStringAnnotations(offset, offset)
                        annotations.firstOrNull()?.let { annotation ->
                            if (annotation.tag == "Link" && annotation.item == "SignUp") {
                                // Handle sign up click event
                            }
                        }
                    },
                    style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                )
            }
        }
    }
    if (showDialog) {
        IncorrectCredentialsDialog(onDismiss = { showDialog = false })
    }
}

@Composable
fun IncorrectCredentialsDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = "Incorrect Credentials")
            },
            text = {
                Text(text = "The username or password you entered is incorrect.")
            },
            confirmButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text("OK")
                }
            }
        )
    }
}

