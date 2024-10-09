package com.terabyte.passworder.activity

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.terabyte.passworder.R
import com.terabyte.passworder.data.DataStoreManager
import com.terabyte.passworder.ui.theme.PassworderTheme
import com.terabyte.passworder.util.HashManager
import com.terabyte.passworder.util.appComponent
import com.terabyte.passworder.util.dataStore
import com.terabyte.passworder.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginActivity : ComponentActivity() {

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModel.Factory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        dev()
        enableEdgeToEdge()
        setContent {
            PassworderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(paddingVals = innerPadding)
                }
            }
        }
    }

    @Composable
    fun MainContent(paddingVals: PaddingValues) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingVals)
        ) {
            val (numBoard, fieldPassword) = createRefs()

            FieldPassword(
                Modifier
                    .constrainAs(fieldPassword) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(numBoard.top)
                        top.linkTo(parent.top)
                    }
            )
            NumBoard(
                Modifier
                    .constrainAs(numBoard) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )


        }
    }

    @Composable
    fun FieldPassword(modifier: Modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .then(modifier)
        ) {
            Text(
                text = "Enter password:",
                fontSize = 24.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                IconPasswordCharacter(number = 1)
                IconPasswordCharacter(number = 2)
                IconPasswordCharacter(number = 3)
                IconPasswordCharacter(number = 4)
            }
        }
    }

    @Composable
    fun NumBoard(modifier: Modifier) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.5f)
                .then(modifier)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ButtonNum(num = "1")
                ButtonNum(num = "2")
                ButtonNum(num = "3")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ButtonNum(num = "4")
                ButtonNum(num = "5")
                ButtonNum(num = "6")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ButtonNum(num = "7")
                ButtonNum(num = "8")
                ButtonNum(num = "9")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ButtonFingerprint()
                ButtonNum(num = "0")
                ButtonBackspace()
            }
        }
    }

    @Composable
    fun IconPasswordCharacter(number: Int) {
        Icon(
            painter = painterResource(id = R.drawable.ic_password_char),
            contentDescription = "char",
            tint = if (viewModel.statePasswordField.value.length >= number) {
                Color.Black
            } else {
                Color.Gray
            }
        )
    }

    @Composable
    fun ButtonNum(num: String) {
        val viewModel: LoginViewModel = viewModel()
        Text(
            text = num,
            color = Color.Blue,
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .clickable {
                    if (viewModel.statePasswordField.value.length < 4) {
                        viewModel.statePasswordField.value += num
                        if (viewModel.statePasswordField.value.length == 4) {
                            checkPassword()
                        }
                    }
                }
        )
    }

    private fun checkPassword() {
        viewModel.checkPassword {
            Toast.makeText(this, "Invalid password", Toast.LENGTH_LONG).show()
        }
    }

    @Composable
    fun ButtonFingerprint() {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(60.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_fingerprint),
                contentDescription = "fingerprint",
                tint = Color.Blue,
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }

    @Composable
    fun ButtonBackspace() {
        val viewModel: LoginViewModel = viewModel()
        IconButton(
            onClick = {
                if(viewModel.statePasswordField.value.length in 1..3) {
                    val passwordLen = viewModel.statePasswordField.value.length
                    viewModel.statePasswordField.value = viewModel.statePasswordField.value.substring(0, passwordLen-1)
                }
            },
            modifier = Modifier
                .size(60.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_backspace),
                contentDescription = "backspace",
                tint = Color.Blue,
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }

    private fun dev() {
        val hashed = HashManager().hash("1111")
        dataStoreManager.writeToDataStore(DataStoreManager.KEY_LOGIN_PASSWORD, hashed) {

        }
    }
}



