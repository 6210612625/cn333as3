package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGame()
        }
    }
}

var play = true
var randomNum: Int = Random.nextInt(1, 5)
var count = 0


@Composable
fun NumberGuessingGame() {
    val input = remember{ mutableStateOf("Try to guess the number I'm thinking of from 1 - 1000!")}
    val answer = remember{ mutableStateOf(TextFieldValue())}
    val submit = remember{ mutableStateOf("")}

    fun reset(){
        randomNum = Random.nextInt(1,1000)
        input.value = "Try to guess the number I'm thinking of from 1 - 1000!"
        play = true
        count = 0
    }


    fun algorithm() {
        if (play) {
            if (answer.value.text.isEmpty()){
                input.value = "Please Enter your guess number."
            }else {
                if (answer.value.text.toInt() < randomNum) {
                    input.value = "Hint: It's higher!"
                    count++

                } else if (answer.value.text.toInt() > randomNum) {
                    input.value = "Hint: It's lower!"
                    count++

                } else {
                    input.value = "Congratulation! you guess $count times"
                    play = false
                }
            }
        } else {
            reset()
        }
    }

    Column(
        modifier = Modifier.padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )

    {
        Text( input.value,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )


        if(play) {
            TextField(
                value = answer.value,
                onValueChange = {answer.value = it},
                singleLine = true,
                placeholder = { Text("Your guess")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
    }

        Button( onClick = { algorithm() } ) {
            if(play) {
                submit.value = "Enter"
                Text(submit.value)
            } else{
                submit.value = "Play again"
                Text(submit.value)
            }

        }

    }
}

