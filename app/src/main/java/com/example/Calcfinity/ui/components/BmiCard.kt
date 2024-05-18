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
    bmicard(
        onDismissRequest = { },
        onConfirmation = { },
        painter = R.drawable.img_2,
        imageDescription = "underweight",
        title = 34.44
    )
}
@Composable
fun bmicard(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Int,
    imageDescription: String,
    title: Double,
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
                    Text(text =title.toString(),
                        maxLines = 1,
                        modifier= Modifier.padding(8.dp) , textAlign = TextAlign.Center , color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold , style = MaterialTheme.typography.displaySmall)
                    Image(
                        painter = painterResource(id = painter),
                        contentDescription = imageDescription,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(160.dp)
                    )
                    Text(
                        text = "According to BMI number you are ${imageDescription} person.",
                        modifier = Modifier.padding(16.dp),
                    )
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