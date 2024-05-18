package com.example.Calcfinity.ui.components

sealed class Actions {
    data class Number(val number: Int) : Actions()
    object Clear : Actions()
    object Delete : Actions()
    object Equal : Actions()
    object Decimal : Actions()
    data class Operator(val operator: Operations) : Actions()
}