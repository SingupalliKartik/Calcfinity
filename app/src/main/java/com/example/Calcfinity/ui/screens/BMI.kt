package com.example.Calcfinity.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Calcfinity.R
import com.example.Calcfinity.ui.components.bmicard
import com.example.Calcfinity.ui.components.card
import com.example.Calcfinity.ui.components.heightCard


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun show() {
    BMI()
}
@Composable
fun BMI() {
    val age = remember { mutableStateOf(23) }
    val weight = remember { mutableStateOf(56) }
    val height = remember { mutableStateOf(170) }

var toShow by remember { mutableStateOf(false) }
    fun calculateBMIStatus(age: Int, bmi: Double): String {
        return when {
            (age >= 0 && age < 2 && bmi < 18.5) -> "Underweight"
            (age >= 0 && age < 2 && bmi >= 18.5 && bmi < 25) -> "Normal weight"
            (age >= 0 && age < 2 && bmi >= 25 && bmi < 30) -> "Overweight"
            (age >= 0 && age < 2 && bmi >= 30) -> "Obese"

            (age >= 2 && age < 18 && bmi < 5) -> "Underweight"
            (age >= 2 && age < 18 && bmi >= 5 && bmi < 85) -> "Normal weight"
            (age >= 2 && age < 18 && bmi >= 85 && bmi < 95) -> "Overweight"
            (age >= 2 && age < 18 && bmi >= 95) -> "Obese"

            (age >= 18 && age < 75 && bmi < 18.5) -> "Underweight"
            (age >= 18 && age < 75 && bmi >= 18.5 && bmi < 25) -> "Normal weight"
            (age >= 18 && age < 75 && bmi >= 25 && bmi < 30) -> "Overweight"
            (age >= 18 && age < 75 && bmi >= 30) -> "Obese"

            (age >= 75 && bmi < 18.5) -> "Underweight"
            (age >= 75 && bmi >= 18.5 && bmi < 25) -> "Normal weight"
            (age >= 75 && bmi >= 25 && bmi < 30) -> "Overweight"
            (age >= 75 && bmi >= 30) -> "Obese"

            else -> "Invalid Data"
        }
    }
    Scaffold(floatingActionButton= {
        FloatingActionButton(onClick = {
//               val total = clickedItems.sumBy { it.value.toInt() * it.calories* it.value.value }
            toShow = true

        }, modifier = Modifier.fillMaxWidth(.92f),) {
            Text(text = "Calculate", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.SemiBold)
        }
    }) {
        val padding = it;
        if(toShow){
            val data = calculateBMIStatus(age.value, (weight.value.toDouble() / ((height.value.toDouble()/100)*(height.value.toDouble()/100)) ))
            bmicard(
                onDismissRequest = { },
                onConfirmation = {
                    toShow = false
                },
                title= (weight.value.toDouble() / ((height.value.toDouble()/100)*(height.value.toDouble()/100)) ),
                painter =
                when(data){
                    "Underweight" -> R.drawable.img_2
                    "Normal weight" -> R.drawable.img
                    "Overweight" -> R.drawable.img_3
                    "Obese" -> R.drawable.img_4
                    else -> R.drawable.img_1
                }


                ,
                imageDescription = data
            )
        }else
        {
            Column(modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween) {

                Column(modifier = Modifier
                    .fillMaxWidth(1f)) {
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    )
                    {
                        card(title = "Age",number=  age, limit = 100)
                        card(title = "Weight(Kg)", number = weight, limit = 100)

                    }
                    heightCard(title = "Height", number =height , climit =250,flimit =10,ilimit =12 )
                    //Text(text =(weight.value.toDouble() / ((height.value.toDouble()/100)*(height.value.toDouble()/100)) ).toString().take(5), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                }


            }
        }

    }


}
