package com.example.Calcfinity.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
private fun show() {

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun card(modifier: Modifier = Modifier, title:String, number:MutableState<Int>, limit:Int) {

    Card(onClick = {},
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
modifier = Modifier
    .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),

    ) {
Column(modifier = Modifier
    .padding(16.dp),

horizontalAlignment = Alignment.CenterHorizontally,
    ){
    Text(text =title, textAlign = TextAlign.Center , color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold , style = MaterialTheme.typography.titleLarge)
    Text(text =number.value.toString(),textAlign = TextAlign.Center , fontWeight = FontWeight.Bold ,color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.displayLarge)
        Row(

            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            OutlinedButton(onClick = {
                                     if(number.value > 3){
                                         number.value--
                                     }

            }, modifier = Modifier.padding(4.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.primary),
            ) {
                Text(text ="-" , color = MaterialTheme.colorScheme.onPrimary, fontSize = 25.sp, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            }
            OutlinedButton(onClick = {
                if(number.value<limit){
                    number.value++
                }
            },modifier = Modifier.padding(4.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.primary),
            ) {
                Text(text ="+" , color = MaterialTheme.colorScheme.onPrimary, fontSize = 25.sp, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            }
        }}
    }
}