package com.terabyte.passworder.viewmodel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terabyte.passworder.activity.MainActivity
import com.terabyte.passworder.data.DataStoreManager
import com.terabyte.passworder.util.HashManager
import com.terabyte.passworder.util.appComponent
import javax.inject.Inject

//private val dataStoreManager: DataStoreManager, private val hashManager: HashManager
class LoginViewModel(private val context: Context): ViewModel() {

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    @Inject
    lateinit var hashManager: HashManager

    val statePasswordField = mutableStateOf("")

    init {
        context.appComponent.inject(this)
    }

    fun checkPassword(failureListener: () -> Unit) {
        val passwd = statePasswordField.value
        val passwdHashed = hashManager.hash(passwd)
        dataStoreManager.readFromDataStore(DataStoreManager.KEY_LOGIN_PASSWORD) { savedPasswd ->
            if(savedPasswd!=null) {
                if (passwdHashed==savedPasswd) {
                    startMainActivity()
                }
                else {
                    statePasswordField.value = ""
                    failureListener()
                }
            }
            else {
                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context.startActivity(intent)
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



