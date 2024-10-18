package com.example.assignment2

import android.graphics.Movie
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingPreview()
        }
    }
}

@Composable
fun UniqueMovieCard() {
    Column(

    ) {
        Box(
            modifier = Modifier.width(111.dp).height(156.dp)
        ){
            Image(painter = painterResource(id=R.drawable.uniq_movie_card),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFF3D3BFF))
                    .padding(4.dp,2.dp)
                    .align(Alignment.TopEnd)
                    .clip(shape = RoundedCornerShape(4.dp))
            ){
                Text(text="7.8",
                    fontSize = 6.sp,
                    lineHeight = 6.6.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
            Image(painter = painterResource(id=R.drawable.eye),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.width(57.dp).height(30.dp)
        ) {
            Text(text="Близкие",
                modifier = Modifier.width(57.dp).height(15.dp),
                fontSize = 14.sp,
                lineHeight = 15.4.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xff272727)
            )
            Text(text="драма",
                modifier = Modifier.width(37.dp).height(13.dp),
                fontSize = 12.sp,
                lineHeight = 13.2.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xff838390)
            )
        }
    }
}
@Composable
fun MovieCard() {
    Column(

    ) {
        Box(
            modifier = Modifier.width(111.dp).height(156.dp)
        ){
            Image(painter = painterResource(id=R.drawable.uniq_movie_card),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFF3D3BFF))
                    .padding(4.dp,2.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.TopEnd)
            ){
                Text(text="7.8",
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
            modifier = Modifier.width(57.dp).height(30.dp)
        ) {
            Text(text="Близкие",
                modifier = Modifier.width(57.dp).height(15.dp),
                fontSize = 14.sp,
                lineHeight = 15.4.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xff272727)
            )
            Text(text="драма",
                modifier = Modifier.width(37.dp).height(13.dp),
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

    ) {
        Spacer(modifier = Modifier.height(55.dp))
        Image(painter = painterResource(id= R.drawable.logo),
            contentDescription = null)
        Spacer(modifier = Modifier.height(46.88.dp))
        LazyColumn() {
            item {CardTitle("Премьеры")}
            item {CardTitle("Популярные")}
            item {CardTitle("Боевики США")}
            item {CardTitle("Топ-250")}
            item {CardTitle("Драмы Франция")}
            item {CardTitle("Сериалы")}
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
                Image(painter = painterResource(id=R.drawable.home),
                    contentDescription = null)
                Image(painter = painterResource(id=R.drawable.search),
                    contentDescription = null)
                Image(painter = painterResource(id=R.drawable.profile),
                    contentDescription = null)
            }
        }
    }
}
@Composable
fun CardTitle(title:String){
    Column (

    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text=title,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 19.8.sp,
                color = Color(0xff272727),
                modifier = Modifier.width(99.dp).height(20.dp).padding(start = 26.dp)
            )
            Spacer(modifier = Modifier.width(185.dp))
            Text(text="Все",
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 15.4.sp,
                color = Color(0xff3d3bff),
                modifier = Modifier.width(24.dp).height(15.dp).padding(end = 26.dp)
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10){
                MovieCard()
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
    }

}
