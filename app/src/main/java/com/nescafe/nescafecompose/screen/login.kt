package com.nescafe.nescafecompose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nescafe.nescafecompose.R
import com.nescafe.nescafecompose.screen.udf.LoginAction
import com.nescafe.nescafecompose.screen.udf.LoginEvent
import kotlinx.coroutines.flow.collectLatest


@Composable
fun LoginView() {
    val vm: LoginViewModel = hiltViewModel()
    val state = vm.loginState.value
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        vm.loginEvent.collectLatest {
            when (it) {
                is LoginEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = it.message)
                }
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        val padding = 16.dp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 120.dp, bottomEnd = 120.dp),
                )
                .padding(all = padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val layoutPadding = 8.dp

            CommonTextField(
                textPlaceholder = "User Name",
                textError = "Incorrect username!",
                value = state.username,
                onTextChanged = {
                    vm.onAction(
                        LoginAction.ChangeUserName(
                            name = it
                        )
                    )
                },
                layoutPadding = layoutPadding,
                isError = state.usernameError,
                leadingIcon = painterResource(id = R.drawable.ic_name),
                shouldShowLeadingIcon = false
            )

            CommonTextField(
                textPlaceholder = "Password",
                textError = "Incorrect password!",
                value = state.password,
                onTextChanged = {
                    LoginAction.ChangePassword(
                        password = it
                    )
                },
                layoutPadding = layoutPadding,
                isError = state.passwordError,
                leadingIcon = painterResource(id = R.drawable.ic_password),
                shouldShowLeadingIcon = true
            )

            Button(
                onClick = {
                    vm.onAction(LoginAction.ClickLogin)
                },
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(48.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Surface {
        LoginView()
    }
}