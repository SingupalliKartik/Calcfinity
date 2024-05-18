package com.example.Calcfinity.ui.components

sealed class Operations(val Symbol: String) {
    object Add : Operations("+")
    object Subtract : Operations("-")
    object Multiply : Operations("x")
    object Divide : Operations("/")

}