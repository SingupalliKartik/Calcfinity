package com.example.Calcfinity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Calcfinity.logic.navhost
import com.example.Calcfinity.ui.screens.BMI
import com.example.Calcfinity.ui.screens.Calculator
import com.example.Calcfinity.ui.screens.Food
import com.example.Calcfinity.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // Create a NavController
                val navController = rememberNavController()

                Scaffold(
                    topBar = { topAppBar(navController) } // Pass the NavController to your topAppBar
                ) {
                    val padding = it
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(it)
                    ) {
                        // Set up a NavHost with the NavController
                        NavHost(navController = navController, startDestination = "Calculator") {
                            composable("Calculator") {
                                Calculator()
                            }
                            composable("BMI") {
                                BMI()
                            }
                            composable("Food") {
                                Food()
                            }
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(

        showBackground = true,
        showSystemUi = true
    )
    @Composable
    private fun Show() {
        Calculator()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun topAppBar(navController: NavHostController) {
        var selectd by remember { mutableStateOf(0) }
        TopAppBar(
            title = { Text( if(selectd == 0) {
                     "Calculator"
            } else if(selectd==1) {
                "BMI"
            } else {
                 "FOOD"
            }) },
            navigationIcon = {
                // Icon(Icons.Filled.Menu, contentDescription = null)
            },
            actions = {
                Row(
                    modifier = Modifier.fillMaxWidth(0.55f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                )

                {


                    list.forEach {

                        Column(
                            modifier = Modifier
                                .height(56.dp)
                                .clip(MaterialTheme.shapes.small)
                                .background(MaterialTheme.colorScheme.surface)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {

                            IconButton(
                                onClick = {
                                    selectd = it.index
                                    navController.navigate(it.title)
                                    it.isSelected = true
                                }, modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .size(30.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = if (selectd == it.index) it.icon else it.ouline),
                                    contentDescription = it.title,
                                )

                            }




                        }
                    }
                }
            },

            )
    }

    data class topnav(
        val title: String,
        val icon: Int,
        val ouline: Int,
        val index: Int,
        var isSelected: Boolean = false,
        val onClick: () -> Unit

    )

    val list = listOf(
        topnav(
            "Calculator",
            R.drawable.baseline_calculate_24,
            R.drawable.outline_calculate_24,
            0
        ) {},
        topnav(
            "BMI",
            R.drawable.baseline_monitor_heart_24,
            R.drawable.outline_monitor_heart_24,
            1
        ) {},
        topnav(
            "Food",
            R.drawable.baseline_brunch_dining_24,
            R.drawable.outline_brunch_dining_24,
            2
        ) {},
    )
}
