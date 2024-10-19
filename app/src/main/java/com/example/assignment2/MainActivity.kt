package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingPreview()
        }
    }
}

data class MovieItem(val title: String, val genre: String, val rating: Double, val imageResId: Int)

val movieList = listOf(
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card),
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card),
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card),
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card),
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card),
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card),
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card),
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card),
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card),
    MovieItem("Близкие", "драма", 7.8, R.drawable.movie_card)
)

@Composable
fun MovieCard(movie: MovieItem,onClick:()->Unit) {
    Column(modifier = Modifier.clickable{onClick}){
        Box(
            modifier = Modifier.width(111.dp).height(156.dp)
        ) {
            Image(
                painter = painterResource(id = movie.imageResId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFF3D3BFF))
                    .padding(4.dp,2.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.TopEnd)
            ) {
                Text(
                    text = movie.rating.toString(),
                    fontSize = 6.sp,
                    lineHeight = 6.6.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.width(111.dp)
        ) {
            Text(
                text = movie.title,
                modifier = Modifier.height(15.dp),
                fontSize = 14.sp,
                lineHeight = 15.4.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xff272727)
            )
            Text(
                text = movie.genre,
                modifier = Modifier.height(13.dp),
                fontSize = 12.sp,
                lineHeight = 13.2.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xff838390)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Column(
        modifier = Modifier.padding(start=26.dp)
    ) {
        Spacer(modifier = Modifier.height(55.dp))
        Image(painter = painterResource(id= R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(120.dp, 18.dp).padding(start = 20.dp))
        Spacer(modifier = Modifier.height(46.88.dp))
        LazyColumn() {
            item {CardTitle("Премьеры" , movies = movieList)}
            item {CardTitle("Популярные", movies = movieList)}
            item {CardTitle("Боевики США", movies = movieList)}
            item {CardTitle("Топ-250", movies = movieList)}
            item {CardTitle("Драмы Франция", movies = movieList)}
            item {CardTitle("Сериалы", movies = movieList)}
        }
        Spacer(modifier = Modifier.height(36.12.dp))
        Box(
            modifier = Modifier
                .background(Color.White)
                .height(64.dp)
                .clip(shape = RoundedCornerShape(16.dp,16.dp))
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ){
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(88.dp,20.dp)
            ){
                Image(painter = painterResource(id=R.drawable.profile),
                    contentDescription = null)
                Image(painter = painterResource(id=R.drawable.profile),
                    contentDescription = null)
                Image(painter = painterResource(id=R.drawable.profile),
                    contentDescription = null)
            }
        }
    }
}


@Composable
fun CardTitle(title: String, movies: List<MovieItem>){
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text=title,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = Color(0xff272727),
                modifier = Modifier.height(20.dp).padding(start = 20.dp)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text="Все",
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 15.4.sp,
                color = Color(0xff3d3bff),
                modifier = Modifier.height(15.dp).padding(end = 26.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(movies.size) { index ->
                val movie = movies[index]
                MovieCard(movie = movie){

                }
            }
            item {
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(111.dp, 156.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id= R.drawable.icon),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp, 32.dp).fillMaxSize())
                        Text(text = "Показать все", fontSize = 12.sp)
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(36.dp))
    }
}

