package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class ListPageScreen : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent(){
            Surface (
                modifier = Modifier.fillMaxSize()
            ){
                val navHostController = rememberNavController()
                NavHost(
                    navController = navHostController,
                    startDestination = "list_page_screen"
                ){
                    composable(route = "list_page_screen"){
                        ListPageScreen(navHostController)
                    }
                    composable(route = "back_screen"){
                        HomeScreen(navHostController)
                    }
                    composable(
                        route = "home_screen"
                    ){
                        HomeScreen(navHostController)
                    }
                    composable(
                        route = "search_screen"
                    ){
                        SearchScreen(navHostController)
                    }
                    composable(
                        route = "profile_screen"
                    ){
                        ProfileScreen(navHostController)
                    }
                }
            }
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
fun ListPageHeader(
    cardTitle: String,
    navHostController: NavHostController
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 26.dp, top = 16.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
                .clickable{navHostController.navigate("back")},
            tint = Color(0xff272727)
        )
        Text(
            text = cardTitle,
            fontWeight = FontWeight.W600,
            fontSize = 12.sp,
            lineHeight = 13.2.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(57.dp).height(13.dp)
                .padding(start = 152.dp, top = 21.5.dp)
        )
    }
}
@Composable
fun ListPageScreen(navHostController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ) {
        ListPageHeader("Сериалы", navHostController)
        ListPageVerticalGrid(movies = movieList)
        ListPageNavigationBar(navHostController)
    }
}
@Composable
fun ListPageVerticalGrid(movies: List<MovieItem>){
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(movies.size) { index ->
            val movie = movies[index]
            MovieCard(movie = movie){

            }
        }
    }
}

@Composable
fun ListPageNavigationBar(
    navHostController: NavHostController
){
        Box (
            modifier = Modifier
                .background(Color.White)
                .height(64.dp)
                .clip(shape = RoundedCornerShape(16.dp,16.dp))
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(88.dp,20.dp).fillMaxWidth()
            ){
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .clickable{navHostController.navigate("home")},
                    tint = Color(0xff272727)
                )
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .clickable{navHostController.navigate("search")},
                    tint = Color(0xff272727)
                )
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .clickable{navHostController.navigate("profile")},
                    tint = Color(0xff272727)
                )
            }
        }
}

@Composable
fun MovieCard(movie: MovieItem,onClick:()->Unit) {
    Column(
        modifier = Modifier.width(111.dp)
    ){
        Box(
            modifier = Modifier.width(111.dp).height(156.dp)
                .background(Color(0xffB5B5C966))
                .clip(RoundedCornerShape(4.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.movie_card),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(4.dp))
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFF3D3BFF))
                    .padding(4.dp,2.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.TopEnd)
            ) {
                Text(
                    text = "7.8",
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
                text = "Близкие",
                modifier = Modifier.height(15.dp),
                fontSize = 14.sp,
                lineHeight = 15.4.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xff272727)
            )
            Text(
                text = "Драма",
                modifier = Modifier.height(13.dp),
                fontSize = 12.sp,
                lineHeight = 13.2.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xff838390)
            )
        }
    }
}


@Composable
fun Premiere(){

}
@Composable
fun Popular(){

}
@Composable
fun ActionMoviesUSA(){

}
@Composable
fun Top250(){

}
@Composable
fun DramaOfFrance(){

}
@Composable
fun Serials(){

}


@Preview(showBackground = true)
@Composable
fun PremierePreview(){
    Premiere()
}
@Preview(showBackground = true)
@Composable
fun PopularPreview(){
    Popular()
}
@Preview(showBackground = true)
@Composable
fun ActionMoviesUSAPreview(){
    ActionMoviesUSA()
}
@Preview(showBackground = true)
@Composable
fun Top250Preview(){
    Top250()
}
@Preview(showBackground = true)
@Composable
fun DramaOfFrancePreview(){
    DramaOfFrance()
}
@Preview(showBackground = true)
@Composable
fun SerialsPreview(){
    Serials()
}