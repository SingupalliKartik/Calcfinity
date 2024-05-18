package com.example.Calcfinity.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Calcfinity.R
import com.example.Calcfinity.logic.foodItem

@Preview
@Composable
private fun show() {
    var value = remember { mutableStateOf(0f) }
   // FoodCard(title="Apple",value=value,Unit="Kg",isClicked=remember { mutableStateOf(false) }){} //Preview for FoodCard
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodCard(title: String, Unit: String, value: MutableState<Int>, list: MutableList<foodItem>, data: foodItem) {
    var isClicked = remember { mutableStateOf(list.contains(data)) }
    Card(onClick = {

        if (isClicked.value) {
           list.removeIf { it.name == data.name }
            isClicked.value = false
        } else {
            isClicked.value = true
            list.add(data)


        }
    },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
            .padding(8.dp)
        ,
        colors = CardDefaults.cardColors(
            if (isClicked.value) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.background
        )

    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(.95f), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(4.dp))
            Image(painter = painterResource(id = R.drawable.img_5), modifier = Modifier
                .fillMaxWidth(1f)
                .clip(MaterialTheme.shapes.medium)
                .border(
                    20.dp, Color.Transparent
                ), contentDescription = "Food basket", contentScale = ContentScale.FillBounds)
            if (isClicked.value) {
                Column {


                    Slider(
                        value = value.value.toFloat(), onValueChange = { newValue ->
                            value.value = newValue.toInt()
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.primary,
                            inactiveTrackColor = MaterialTheme.colorScheme.primaryContainer,
                            disabledInactiveTrackColor = MaterialTheme.colorScheme.background,
                        ),
                        steps = 4,
                        valueRange = 1f..5f
                    )
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                        Text(text = "-", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        Text(text = "+", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold,color = MaterialTheme.colorScheme.primary)

                    }
                }
                Row {
                    Text(text = value.value.toInt().toString(), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = Unit, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
    }}