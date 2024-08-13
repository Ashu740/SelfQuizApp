package com.example.flashcardquiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashcardquiz.viewModel.CounterViewModel

@Composable
fun QuestionCard(modifier: Modifier, question: String, answer: String, options: List<String>, navController: NavController){
    val viewModel : CounterViewModel = viewModel()
    Column(modifier = modifier.padding(8.dp)
    ) {
        Text(text = "Q: $question", fontSize = 50.sp)
        val modified = (options + answer).shuffled()
        modified.forEach { option->
            val isCorrect = option == answer
            OptionCard(option = option, answer = answer, isCorrect = isCorrect)
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            navController.navigate("/score/${viewModel.count.value.toString()}")
        },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Submit" ,
                fontSize = 14.sp,
                color = Color.White)
        }
    }
}

