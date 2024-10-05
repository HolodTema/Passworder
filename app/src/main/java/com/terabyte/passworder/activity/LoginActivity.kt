package com.terabyte.passworder.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.terabyte.passworder.R
import com.terabyte.passworder.activity.ui.theme.PassworderTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PassworderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
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
            Icon(
                painter = painterResource(id = R.drawable.ic_password_char),
                contentDescription = "char",
                tint = Color.Gray
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_password_char),
                contentDescription = "char",
                tint = Color.Gray
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_password_char),
                contentDescription = "char",
                tint = Color.Gray
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_password_char),
                contentDescription = "char",
                tint = Color.Gray
            )
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
fun ButtonNum(num: String) {
        Text(
            text = num,
            color = Color.Blue,
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .clickable {

                }
        )
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
    IconButton(
        onClick = { /*TODO*/ },
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

