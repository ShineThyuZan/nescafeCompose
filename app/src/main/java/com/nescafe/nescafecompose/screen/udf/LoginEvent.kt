package com.nescafe.nescafecompose.screen.udf

sealed class LoginEvent{
    data class ShowSnackBar(val message: String) : LoginEvent()
}