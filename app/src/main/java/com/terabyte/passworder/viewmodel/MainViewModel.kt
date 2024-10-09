package com.terabyte.passworder.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terabyte.passworder.data.room.Password
import com.terabyte.passworder.data.room.RoomManager
import com.terabyte.passworder.util.appComponent
import javax.inject.Inject


class MainViewModel(context: Context): ViewModel() {

    @Inject
    lateinit var roomManager: RoomManager

    val statePasswords = mutableStateOf(listOf<Password>())


    init {
        context.appComponent.inject(this)

        roomManager.getAllPasswords {
            statePasswords.value = it
        }
    }


    class Factory(private val context: Context): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return modelClass.getConstructor(Context::class.java).newInstance(context)
            }
            else {
                throw IllegalArgumentException("Incorrect ViewModel type")
            }
        }
    }
}



