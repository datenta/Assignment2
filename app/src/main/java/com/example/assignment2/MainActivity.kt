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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    Column() {
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

}