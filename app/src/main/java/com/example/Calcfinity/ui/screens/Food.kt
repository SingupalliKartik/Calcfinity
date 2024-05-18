package com.example.Calcfinity.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Calcfinity.logic.foodItem
import com.example.Calcfinity.logic.foodList
import com.example.Calcfinity.ui.components.FoodCard
import com.example.Calcfinity.ui.components.totalFood

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
private fun show() {


    Food()
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Food() {
    val clickedItems = remember { mutableStateListOf<foodItem>() }
    val text = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val ischeck = remember {
        mutableStateOf(false)
    }
   Scaffold(
       topBar = {
                TopAppBar(title = {

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start,verticalAlignment = Alignment.CenterVertically) {


                    OutlinedTextField(value =text.value ,
                        label = { Text("Search") },
                        maxLines = 1,
                        textStyle = TextStyle(color = Color.Unspecified, fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(.96f),
                        onValueChange ={text.value=it} ,
                        trailingIcon = {
                            IconButton(onClick = {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }){
                                Icon(imageVector = Icons.Filled.Search, contentDescription = "Searchbar")
                            }


                        },



                    )


                }
                })
       },
       floatingActionButton= {
           FloatingActionButton(onClick = {
//               val total = clickedItems.sumBy { it.value.toInt() * it.calories* it.value.value }
               ischeck.value=!ischeck.value
               println("Total calories: 0")
           }, modifier = Modifier.fillMaxWidth(.92f),) {
               Text(text = "Calculate", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.SemiBold)}
       }
   ) {
if(ischeck.value){
  totalFood(
      onDismissRequest = { /**/ },
      onConfirmation = {

          ischeck.value = false
          },
      Calories = clickedItems.sumOf { it.calories* it.value.value }.toDouble(),
      Protein = clickedItems.sumOf { it.proteins* it.value.value }.toDouble(),
      Carbs = clickedItems.sumOf { it.carbs* it.value.value }.toDouble(),
      Fat = clickedItems.sumOf { it.fats* it.value.value }.toDouble(),

  )
}else
{
       LazyVerticalStaggeredGrid(
           columns = StaggeredGridCells.Fixed(2),
           modifier = Modifier
               .fillMaxHeight()
               .padding(it)
               .padding(10.dp),
           verticalItemSpacing = 4.dp,
           horizontalArrangement = Arrangement.spacedBy(4.dp),
       ){
           val filteredList = foodList.filter {
             it.name.contains(text.value,true)
           }
           items(filteredList) { data ->
               FoodCards(data = data, clickedItems = clickedItems)
           }
       }

   }}
}
@Composable
fun FoodCards(data: foodItem, clickedItems: MutableList<foodItem>) {

    FoodCard(title = data.name, Unit = data.unit, value = data.value, list = clickedItems, data = data )
}
