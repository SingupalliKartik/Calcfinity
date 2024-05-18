package com.example.Calcfinity.logic

// CalculatorLogic.kt
class CalculatorLogic {
    fun add(num1: Double, num2: Double): Double = num1 + num2
    fun subtract(num1: Double, num2: Double): Double = num1 - num2
    fun multiply(num1: Double, num2: Double): Double = num1 * num2
    fun divide(num1: Double, num2: Double): Double {
        if (num2 == 0.0) {
            throw IllegalArgumentException("Cannot divide by zero")
        }
        return num1 / num2
    }

    fun calculate(num1: Double, num2: Double, operator: String): Double {
        return when (operator) {
            "+" -> add(num1, num2)
            "-" -> subtract(num1, num2)
            "*" -> multiply(num1, num2)
            "/" -> divide(num1, num2)
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}