package com.example.flashcardquiz

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardquiz.viewModel.CounterViewModel

@Composable
fun OptionCard(option: String, answer: String, isCorrect: Boolean){
    val viewModel : CounterViewModel = viewModel()
    var cardColor by remember { mutableStateOf(Color.Transparent) }
    Card(onClick = {
        cardColor = if(isCorrect){
            viewModel.increment()
            Color.Green
        }else{
            Color.Red
        }
    },
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        colors = CardColors(containerColor = cardColor, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = Color.White)
    ) {
        Text(text = option, color = MaterialTheme.colorScheme.onSurface, fontSize = 42.sp, textAlign = TextAlign.Center, modifier = Modifier.align(
            Alignment.CenterHorizontally))
    }
}