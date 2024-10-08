package com.terabyte.passworder.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.internal.Provider
import javax.inject.Inject


class LoginViewModel(): ViewModel() {

    val statePasswordField = mutableStateOf("")

    fun checkPassword() {

    }

    class Factory: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return modelClass.getConstructor().newInstance()
            }
            else {
                throw IllegalArgumentException("Incorrect ViewModel type")
            }
        }
    }
}



