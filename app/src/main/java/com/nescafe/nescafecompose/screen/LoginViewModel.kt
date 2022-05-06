package com.nescafe.nescafecompose.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nescafe.nescafecompose.screen.udf.LoginAction
import com.nescafe.nescafecompose.screen.udf.LoginEvent
import com.nescafe.nescafecompose.screen.udf.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> get() = _loginState

    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent: SharedFlow<LoginEvent> get() = _loginEvent

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.ChangePassword -> {
                _loginState.value = loginState.value.copy(
                    password = action.password,
                    passwordError = false
                )
            }
            is LoginAction.ChangeUserName -> {
                _loginState.value = loginState.value.copy(
                    username = action.name,
                    usernameError = false
                )
            }
            LoginAction.ClickLogin -> {
                validateLogin()
            }
        }
    }

    private fun validateLogin() {
        if(loginState.value.username != "Thar Po") {
            _loginState.value = loginState.value.copy(
                usernameError = true
            )
        }
        if(loginState.value.password != "password") {
            _loginState.value = loginState.value.copy(
                passwordError = true
            )
        }

        if(loginState.value.username == "Thar Po" && loginState.value.password == "password") {
            viewModelScope.launch {
                _loginEvent.emit(LoginEvent.ShowSnackBar(message = "Success"))
            }
        } else {
            viewModelScope.launch {
                _loginEvent.emit(LoginEvent.ShowSnackBar(message = "Fail"))
            }
        }
    }
}