package com.nescafe.nescafecompose.screen.udf

sealed class LoginAction{
    object ClickLogin : LoginAction()
    data class ChangeUserName(val name: String) : LoginAction()
    data class ChangePassword (val password: String): LoginAction()
}
