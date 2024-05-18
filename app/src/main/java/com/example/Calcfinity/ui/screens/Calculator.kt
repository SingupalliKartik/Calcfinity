package com.example.Calcfinity.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.Calcfinity.logic.CalculatorLogic
import com.example.Calcfinity.ui.components.CustomBTN

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.NEXUS_5
)
@Composable
private fun show() {
Calculator()
}
@Composable
fun Calculator () {
    val num = remember { mutableStateOf("0") }
    val ans  = remember { mutableStateOf("0") }
    Scaffold {
        val paddin = it;

            Column(modifier = Modifier.padding(5.dp,10.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                input(nums = num)
                answer(ans)
            }

            buttons(nums = num,value = ans)

    }


}
@Composable
fun input(nums: MutableState<String>) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp,2.dp)
        .horizontalScroll(rememberScrollState(), reverseScrolling = true),
        horizontalArrangement = Arrangement.End,
        verticalAlignment   = Alignment.Bottom
    ) {
        Text(
            text = nums.value,
            style = MaterialTheme.typography.displayLarge,
            maxLines = 2,)
    }

}

@Composable
fun buttons(nums: MutableState<String>,value: MutableState<String>) {
Column(modifier = Modifier
    .padding(10.dp)
    .fillMaxWidth()
    .fillMaxHeight(),
    verticalArrangement = Arrangement. Bottom,
    horizontalAlignment = Alignment.CenterHorizontally) {
    Divider()
    Spacer(modifier =Modifier.height(15.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        CustomBTN("C",nums,value ,MaterialTheme.colorScheme.secondary)
        CustomBTN("+/-",nums,value, MaterialTheme.colorScheme.secondary)
        CustomBTN("%",nums,value, MaterialTheme.colorScheme.secondary)
        CustomBTN("/",nums,value, MaterialTheme.colorScheme.primary)
    }
Spacer(modifier =Modifier.height(8.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        CustomBTN("1",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("2",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("3",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("x",nums,value, MaterialTheme.colorScheme.primary)
    }
    Spacer(modifier =Modifier.height(8.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        CustomBTN("4",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("5",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("6",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("-",nums,value, MaterialTheme.colorScheme.primary)
    }
    Spacer(modifier =Modifier.height(8.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        CustomBTN("7",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("8",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("9",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("+",nums,value, MaterialTheme.colorScheme.primary)
    }
    Spacer(modifier =Modifier.height(8.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        CustomBTN(".",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("0",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("del",nums,value, MaterialTheme.colorScheme.background)
        CustomBTN("=", nums,value, MaterialTheme.colorScheme.primary)
    }
    Spacer(modifier =Modifier.height(8.dp))

}
    }
//{nums.value = nums.value.dropLast(1)}
@Composable
private fun calculaters(nums: MutableState<String>) = {
    val num1 = nums.value.substringBeforeLast(" ").toDouble()
    val num2 = nums.value.substringAfterLast(" ").toDouble()
    val operator = nums.value[nums.value.indexOf(" ")].toString()
    val calculatorLogic = CalculatorLogic()
    try {
        val result = calculatorLogic.calculate(num1, num2, operator)
        // Display the result
    } catch (e: IllegalArgumentException) {
        // Display the error messagey
        println(e.message)
    }
}

@Composable
fun answer(ans:MutableState<String>) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp,2.dp)
        .horizontalScroll(rememberScrollState(), reverseScrolling = true),
        horizontalArrangement = Arrangement.End,
        verticalAlignment   = Alignment.Bottom) {
        Text(text = ans.value.toDouble().toString(), style = MaterialTheme.typography.displaySmall, maxLines = 2,)
         }
}
