package com.example.lemonadecompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadecompose.ui.theme.LemonadeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeComposeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {

    var currentStep by rememberSaveable { mutableStateOf(1) }
    var clicksNeeded by remember { mutableStateOf(0) }

    if (currentStep == 2) {
        clicksNeeded = (2..4).random()
    }

    fun nextStep() {
        if (currentStep == 2) {
            clicksNeeded--
        }
        if (clicksNeeded == 0) {
            currentStep++
        }
        if (currentStep > 4) {
            currentStep = 1
        }
    }
    Steps(
        stepNumber = currentStep,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) { nextStep() }
}

@Composable
fun Steps(
    stepNumber: Int,
    modifier: Modifier = Modifier,
    changeStep: () -> Unit
) {
    val stepInstructions = when (stepNumber) {
        1 -> stringResource(R.string.lemon_tree_instruction)
        2 -> stringResource(R.string.lemon_squeeze_instruction)
        3 -> stringResource(R.string.lemon_drink_instruction)
        else -> stringResource(R.string.lemon_restart_instruction)
    }

    val imageResource = when (stepNumber) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stepInstructions, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            modifier = Modifier
                .clickable(
                    onClick = changeStep
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 215)),
                    shape = RoundedCornerShape(4.dp)
                ),
            painter = painterResource(id = imageResource),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeComposeTheme {

    }
}