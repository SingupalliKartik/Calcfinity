package com.example.Calcfinity.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Stack

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun showone() {
   // CustomBTN("1",{}, MaterialTheme.colorScheme.primary)

}

@Composable
fun CustomBTN(
    text: String,
    nums: MutableState<String>,
    value: MutableState<String>,

    color: Color ,
) {
    val isOperatorLast = nums.value.isNotEmpty() && nums.value.last() in "+-x/%"
    OutlinedButton(
        onClick = {
            if (text == "+/-" && isOperatorLast) {
                // Do nothing if the last character is an operator and "+/-" button is clicked
                return@OutlinedButton
            }
            Log.d("Button Clicked",text)
            CoroutineScope(Dispatchers.Default).launch {
                if(text=="="){
                    val result = calculate(nums.value)
                    withContext(Dispatchers.Main) {
                        if (result != "Invalid") {
                            if (result != null) {
                                nums.value = result
                            }
                            if (result != null) {
                                value.value = result
                            }
                        } else {
                            nums.value = "Invalid"
                            value.value = "Invalid"
                        }
                    }
                }
                else{
                    withContext(Dispatchers.Main) {
                        nums.value = concate(nums,text,value)
                        if(text!=".") {
                            val result = calculate(nums.value)
                            if (result != "Invalid") {
                                if (result != null) {
                                    value.value = result
                                }
                            } else {
                                value.value = "Invalid"
                            }
                        }
                    }
                }
            }
        },
        modifier = Modifier
            .width(70.dp)
            .height(70.dp)
            .clip(CircleShape)
            .background(color = color),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = color),
        border = BorderStroke(1.dp, Color.Transparent),
        contentPadding = PaddingValues(15.dp)

    ) {
        Text(text = text, fontSize = 22.sp, color = (if(color !=MaterialTheme.colorScheme.surface ) {
            MaterialTheme.colorScheme.onError
        }else {
            MaterialTheme.colorScheme.onBackground
        } ))
    }
}
fun calculate(expression: String): String? {
    val values = Stack<Double>()
    val ops = Stack<Char>()
    var i = 0
    while (i < expression.length) {
        if (expression[i] == ' ') {
            i++
            continue
        }
        if (expression[i] in '0'..'9' || expression[i] == '.' || (i > 0 && expression[i] == '-' && expression[i - 1] in "+-x/")) {
            var num = ""
            while (i < expression.length && (expression[i] in '0'..'9' || expression[i] == '.' || (i > 0 && expression[i] == '-' && expression[i - 1] in "+-x/"))) {
                num += expression[i]
                i++
            }
            if (num == "+0") num = "0"
            values.push(num.toDouble())
        } else if (expression[i] == '(') {
            ops.push(expression[i])
            i++
        } else if (expression[i] == ')') {
            while (ops.isNotEmpty() && ops.peek() != '('&& values.size>1) {
                values.push(applyOp(ops.pop(), values.pop(), values.pop()))
            }
            ops.pop()
            i++
        } else if (expression[i] == '+' || expression[i] == '-' || expression[i] == 'x' || expression[i] == '/' || expression[i] == '%') {
            if(expression[i] == '-' && (i == 0 || expression[i - 1] in "+-x/")) {
                i++
                var num = "-"
                while (i < expression.length && (expression[i] in '0'..'9' || expression[i] == '.' || (i > 0 && expression[i] == '-' && expression[i - 1] in "+-x/"))) {
                    num += expression[i]
                    i++
                }
                values.push(num.toDouble())
                continue
            }
            while (ops.isNotEmpty() && hasPrecedence(expression[i], ops.peek())) {
                values.push(applyOp(ops.pop(), values.pop(), values.pop()))
            }
            ops.push(expression[i])
            i++
        }
    }

    while (ops.isNotEmpty()&& values.size>1) {
        try {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()))
        } catch (e: Exception) {
            return "Invalid argument"
        }
    }
    return values.pop().toString()

}

fun hasPrecedence(op1: Char, op2: Char): Boolean {
    if (op2 == '(' || op2 == ')') return false
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false
    return true
}
fun applyOp(op: Char, b: Double, a: Double): Double {
    if (a == null || b == null) {
        throw IllegalArgumentException("Not enough values in the stack to apply operation")
    }
    return when (op) {
        '+' -> a + b
        '-' -> a - b
        'x' -> a * b
        '/' -> {
            if (b == 0.0) {
                if (a >= 0) Double.POSITIVE_INFINITY else Double.NEGATIVE_INFINITY
            } else {
                a / b
            }
        }
        '%' -> a / 100 * b
        else -> throw IllegalArgumentException("Invalid operator")
    }
}

fun concate(nums: MutableState<String>, text: String, value: MutableState<String>): String {
    if (text == ".") {
        return if (nums.value.split("[-+x/%]".toRegex()).last().contains(".")) {
            nums.value
        } else {
            nums.value + text
        }
    }
    if (text == "C") {
        return "0"
    }
    if (text == "del") {
        return if (nums.value.length > 1) {
            nums.value.dropLast(1)
        } else {
            "0"
        }
    }
    if (text == "+/-") {
        val lastOperatorIndex = nums.value.lastIndexOfAny(listOf("+", "-", "x", "/", "%"))
        if (lastOperatorIndex != -1) {
            var number = nums.value.substring(lastOperatorIndex + 1)
            if (nums.value[lastOperatorIndex] == '+') {
                return nums.value.substring(0, lastOperatorIndex + 1) + if (number.startsWith("-")) number.substring(1) else "-" + number
            } else if (nums.value[lastOperatorIndex] == '-') {
                if (lastOperatorIndex - 1 != -1 && (nums.value[lastOperatorIndex - 1] == 'x' || nums.value[lastOperatorIndex - 1] == '/' || nums.value[lastOperatorIndex - 1] == '+')) {
                    return nums.value.substring(0, lastOperatorIndex) + number
                } else {
                    return nums.value.substring(0, lastOperatorIndex) + if (number.startsWith("-")) number.substring(1) else "+" + number
                }
            } else {
                return nums.value.substring(0, lastOperatorIndex + 1) + if (number.startsWith("-")) number.substring(1) else "-" + number
            }
        } else {
            return if (nums.value.startsWith("-")) nums.value.substring(1) else "-" + nums.value
        }
    }

    if (text == "+" || text == "-" || text == "x" || text == "/" || text == "%") {
        if (nums.value.startsWith("+")) {
            return nums.value.substring(1) + text
        } else if (nums.value.last().toString() != "+" && nums.value.last().toString() != "-" && nums.value.last().toString() != "x" && nums.value.last().toString() != "/" && nums.value.last().toString() != "%") {
            return nums.value + text
        } else {
            return nums.value.dropLast(1) + text
        }
    } else {
        if (nums.value == "0" || nums.value == "0.0") {
            return text
        } else {
            return nums.value + text
        }
    }
}
