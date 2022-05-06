package com.nescafe.nescafecompose.screen.udf

data class LoginState(
    val username : String = "",
    val password : String = "",
val usernameError : Boolean = false,
val passwordError : Boolean = false,

)
