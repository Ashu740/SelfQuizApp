package com.example.flashcardquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.flashcardquiz.data.model.QuizEntity
import com.example.flashcardquiz.viewModel.AddScreenViewModel
import com.example.flashcardquiz.viewModel.AddScreenViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun AddScreen(category: String, navController: NavController){
    val viewModel: AddScreenViewModel = viewModel(
        factory = AddScreenViewModelFactory(LocalContext.current)
    )
    val coroutineScope = rememberCoroutineScope()
    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, card) = createRefs()
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 16.dp, end = 16.dp)
                .constrainAs(nameRow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ){
                Image(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable { navController.popBackStack() },
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )
                Text(text = "Add Expense",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }
            DataForm(category,modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, onAddQuizClick ={
                coroutineScope.launch {
                    if(viewModel.addQuiz(it)){
                        navController.popBackStack()
                    }
                }
            })
        }
    }
}

@Composable
fun DataForm(category: String, modifier: Modifier, onAddQuizClick: (QuizEntity) -> Unit){
    Column(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(color = MaterialTheme.colorScheme.surfaceBright)
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        val question = remember {
            mutableStateOf("")
        }
        val answer = remember{
            mutableStateOf("")
        }
        val option1 = remember {
            mutableStateOf("")
        }
        val option2 = remember {
            mutableStateOf("")
        }
        val option3 = remember {
            mutableStateOf("")
        }

        Text(text = "Question", fontSize = 14.sp)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(value = question.value, onValueChange = {
            question.value = it
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(8.dp))

        Text(text = "Answer", fontSize = 14.sp)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(value = answer.value, onValueChange = {
            answer.value = it
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Option One", fontSize = 14.sp)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(value = option1.value, onValueChange = {
            option1.value = it
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Option One", fontSize = 14.sp)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(value = option2.value, onValueChange = {
            option2.value = it
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Option One", fontSize = 14.sp)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(value = option3.value, onValueChange = {
            option3.value = it
        }, modifier = Modifier.fillMaxWidth())


        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            val quiz = QuizEntity(
                null,
                category,
                question.value,
                answer.value,
                listOf(option1.value, option2.value, option3.value)
            )
            onAddQuizClick(quiz)
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
            Text(text = "Add Quiz" ,
                fontSize = 14.sp,
                color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAddScreen(){
    AddScreen("",navController = rememberNavController())
}