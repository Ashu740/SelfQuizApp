package com.example.flashcardquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.flashcardquiz.viewModel.HomeViewModel
import com.example.flashcardquiz.viewModel.HomeViewModelFactory
import java.util.Locale.Category
import kotlin.math.round
import com.example.flashcardquiz.CategoryItem

@Composable
fun Home(navController: NavController){
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(LocalContext.current)
    )
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(top = 60.dp, start = 16.dp, end = 16.dp)
    ) {

        val (name, card) = createRefs()
        Text(text = "Quiz Now", fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(name){
                 start.linkTo(parent.start)
            },
            fontSize = 50.sp,
            color = MaterialTheme.colorScheme.onSurface)
            Box(modifier = Modifier
                .fillMaxSize()
                .constrainAs(card) {
                    top.linkTo(name.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                }
            ) {
                Category(navController, listOf("Maths", "Science", "History"))
            }

    }
}


@Composable
fun Category (navController: NavController, list: List<String> ){
    LazyRow(modifier = Modifier.fillMaxSize()) {
        items(list){category ->
            CategoryItem(navController, category)
        }
    }
}

@Composable
fun CategoryItem(navController: NavController, category: String){

        Card(onClick = { navController.navigate("/quiz/${category}") }, modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))) {
            Box(modifier = Modifier.padding(16.dp).height(500.dp)) {
                Text(
                    text = "$category Quiz",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 45.sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
        }
}


@Preview(showBackground = true)
@Composable
fun PreViewHome(){
    Home(rememberNavController())
}
