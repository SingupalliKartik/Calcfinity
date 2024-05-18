package com.example.Calcfinity.ui.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.Calcfinity.R

@Preview
@Composable
private fun show() {

}
@Composable
fun totalFood(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    Calories:Double,
    Protein:Double,
    Carbs:Double,
    Fat: Double,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()

                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(


                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "Calories:",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text =Calories.toString()+" kcal",modifier= Modifier.padding(8.dp) , textAlign = TextAlign.Center , color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold , style = MaterialTheme.typography.headlineSmall)
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "Protein:",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text =Protein.toString()+" g",modifier= Modifier.padding(8.dp) , textAlign = TextAlign.Center , color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold , style = MaterialTheme.typography.headlineSmall)
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "Carbs:",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text =Carbs.toString()+" g",modifier= Modifier.padding(8.dp) , textAlign = TextAlign.Center , color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold , style = MaterialTheme.typography.headlineSmall)
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "Fat:",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text =Fat.toString()+" g",modifier= Modifier.padding(8.dp) , textAlign = TextAlign.Center , color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold , style = MaterialTheme.typography.headlineSmall)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {

                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }

}