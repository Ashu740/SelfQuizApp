package com.example.flashcardquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

@Composable
fun Score(total: String, navController: NavController){
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(top = 60.dp, start = 8.dp, end = 8.dp)) {
        ConstraintLayout {
            val (score, button ) = createRefs()
            Text(text = total, fontSize = 60.sp, color = Color.Green, textAlign = TextAlign.Center, modifier = Modifier.constrainAs(score){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            Button(onClick = {
                navController.popBackStack("/home", inclusive = false)
            }, modifier = Modifier
                .fillMaxWidth()
                .constrainAs(button){
                    top.linkTo(score.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }){
                Text(text = "Go Back")
            }
        }
    }
}

