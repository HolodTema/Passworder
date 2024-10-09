package com.terabyte.passworder.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terabyte.passworder.data.datastore.DataStoreManager
import com.terabyte.passworder.util.HashManager
import com.terabyte.passworder.util.appComponent
import javax.inject.Inject


class LoginViewModel(context: Context): ViewModel() {

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    @Inject
    lateinit var hashManager: HashManager

    val statePasswordField = mutableStateOf("")

    init {
        context.appComponent.inject(this)
    }

    fun checkPassword(successListener: () -> Unit, failureListener: () -> Unit) {
        val passwd = statePasswordField.value
        val passwdHashed = hashManager.hash(passwd)
        dataStoreManager.readFromDataStore(DataStoreManager.KEY_LOGIN_PASSWORD) { savedPasswd ->
            if(savedPasswd!=null) {
                if (passwdHashed==savedPasswd) {
                    successListener()
                }
                else {
                    statePasswordField.value = ""
                    failureListener()
                }
            }
            else {
                successListener()
            }
        }
    }


    class Factory(private val context: Context): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return modelClass.getConstructor(Context::class.java).newInstance(context)
            }
            else {
                throw IllegalArgumentException("Incorrect ViewModel type")
            }
        }
    }
}



