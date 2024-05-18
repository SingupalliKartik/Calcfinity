package com.example.Calcfinity.logic

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Calcfinity.ui.screens.BMI
import com.example.Calcfinity.ui.screens.Calculator
import com.example.Calcfinity.ui.screens.Food
@Composable
fun navhost() {
    val navController = rememberNavController()
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
//
}