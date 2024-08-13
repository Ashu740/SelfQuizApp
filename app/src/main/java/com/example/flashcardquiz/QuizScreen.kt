package com.example.flashcardquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.flashcardquiz.viewModel.QuizViewModel
import com.example.flashcardquiz.viewModel.QuizViewModelFactory

@Composable
fun QuizScreen(category: String, navController: NavController){
    val viewModel : QuizViewModel = viewModel(
        factory = QuizViewModelFactory(LocalContext.current, category)
    )
    val state = viewModel.getQuizByCategory.collectAsState(initial = emptyList())
    val item = state.value
    ConstraintLayout (modifier = Modifier
        .fillMaxSize()
        .padding(top = 60.dp, start = 16.dp, end = 16.dp)){

        val (main, add) = createRefs()

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(main) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            if (item.isEmpty()) {
                item {
//                    Text("No quizzes found")
                    QuestionCard(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), question = "2+2", answer = "4", options = listOf("2", "3", "5"),
                        navController
                    )
                }
            } else {
                items(item) { quiz ->
                    QuestionCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        question = quiz.ques,
                        answer = quiz.correctAnswer,
                        options = quiz.incorrectAnswer,
                        navController
                    )
                    // Add navigation or other interactions here
                }
            }
        }
        Image(painter = painterResource(id = R.drawable.baseline_add_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier
                .constrainAs(add) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .size(100.dp)
                .clip(CircleShape)
                .padding(bottom = 40.dp)
                .clickable {
                    navController.navigate("/add/${category}")
                }
        )
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen(){
    QuizScreen("",navController = rememberNavController())
}