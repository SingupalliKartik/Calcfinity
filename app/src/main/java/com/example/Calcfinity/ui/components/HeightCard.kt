package com.example.Calcfinity.ui.components


import android.widget.ToggleButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Calcfinity.R

@Preview
@Composable
private fun show() {
    val weight = remember { mutableStateOf(120) }
//    heightCard(title = "Height", number =weight , limit =300 )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun heightCard(modifier: Modifier = Modifier, title:String, number:MutableState<Int>, climit:Int,flimit:Int,ilimit:Int) {
    var value by remember { mutableStateOf( number.value/2.54) }
    var feet by remember { mutableStateOf( number.value/30.48) }
    var inch  by remember { mutableStateOf( (number.value/2.54)%12) } // Use modulus to get the remainder
    var checked by remember { mutableStateOf(false) }
    Card(onClick = {},
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
        Column(modifier = Modifier
            .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
                ){


                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge
                )
Row(  verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround
) {
Text(text = "inch", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(2.dp))
    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        }
    )
Text(text = "cm", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodyLarge ,modifier = Modifier.padding(2.dp))


}


            }
            if(checked)
            {
                Card(
                    onClick = {},
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    ) {
                        IconButton(
                            onClick = {
                                if (number.value > 0) {
                                    number.value--
                                    feet = number.value / 2.54 / 12 // Update feet
                                    inch = number.value / 2.54 % 12
                                }

                            },
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight(),
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor =
                                MaterialTheme.colorScheme.secondary
                            ),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
                                contentDescription = "down",
                                modifier = Modifier.height(100.dp)
                            )
                        }


                        Text(
                            text = number.value.toString(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.displayMedium
                        )
                        IconButton(
                            onClick = {
                                if (number.value < climit) {
                                    number.value++
                                    feet = number.value / 2.54 / 12 // Update feet
                                    inch = number.value / 2.54 % 12
                                }

                            },
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight(),
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor =
                                MaterialTheme.colorScheme.secondary
                            ),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_upward_24),
                                contentDescription = "up",
                                modifier = Modifier.height(100.dp)
                            )
                        }
                    }

                }
            }
            else{
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        onClick = {},
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(0.48f)
                        ,
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                    )
                    {
                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth(1f)

                                .height(180.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    if (feet < flimit) {
                                        number.value += 30.48.toInt() // Convert feet to cm
                                        feet  ++ // Update feet
                                       // Update inch
                                    }
                                },
                                modifier = Modifier
                                    .padding(4.dp),
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor =
                                    MaterialTheme.colorScheme.secondary
                                ),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_arrow_upward_24),
                                    contentDescription = "down",
                                    modifier = Modifier.height(50.dp)
                                )
                            }

                            Text(
                                text = feet.toInt().toString()+"'",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondary,
                                style = MaterialTheme.typography.displaySmall
                            )
                            IconButton(
                                onClick = {
                                    if (feet > 1 && number.value > 30.48) {
                                        number.value -= 30.48.toInt() // Convert feet to cm
                                        feet -- // Update feet
                                         // Update inch
                                    }
                                },
                                modifier = Modifier
                                    .padding(4.dp),
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor =
                                    MaterialTheme.colorScheme.secondary
                                ),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
                                    contentDescription = "down",
                                    modifier = Modifier.height(50.dp)
                                )
                            }

                        }

                    }

                    Card(
                        onClick = {},
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(1f)
                        ,
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                    )
                    {
                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth(1f)

                                .height(180.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    if (inch < ilimit) {
                                        number.value += 2.54.toInt() // Convert feet to cm
                                      // Update feet
                                        inch ++ // Update inch
                                    }
                                },
                                modifier = Modifier
                                    .padding(4.dp),
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor =
                                    MaterialTheme.colorScheme.secondary
                                ),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_arrow_upward_24),
                                    contentDescription = "down",
                                    modifier = Modifier.height(50.dp)
                                )
                            }

                            Text(
                                text = inch.toInt().toString()+"''",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondary,
                                style = MaterialTheme.typography.displaySmall
                            )
                            IconButton(
                                onClick = {
                                    if (inch > 0) {
                                        number.value -= 2.54.toInt() // Convert feet to cm

                                        inch -- // Update inch
                                    }
                                },
                                modifier = Modifier
                                    .padding(4.dp),
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor =
                                    MaterialTheme.colorScheme.secondary
                                ),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
                                    contentDescription = "down",
                                    modifier = Modifier.height(50.dp)
                                )
                            }

                        }

                    }

                }
            }
        }
    }
}