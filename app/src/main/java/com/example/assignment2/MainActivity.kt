package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.ui.theme.Assignment2Theme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                MyScreen()
                //ScreenContent()

            }
        }
    }
}

sealed class Screen(val route: String, val icon: Int) {
    object Home : Screen("home", R.drawable.home)
    object Search : Screen("search", R.drawable.search)
    object Profile : Screen("profile", R.drawable.profile)
    object MovieDetail : Screen("movie_detail/{title}/{genre}/{rating}/{imageResId}", R.drawable.profile) {
        fun createRoute(title: String, genre: String, rating: Double, imageResId: Int) =
            "movie_detail/$title/$genre/$rating/$imageResId"
    }
}

@Composable
fun MyScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) { HomePage(navController) }
            composable(Screen.Search.route) { SearchPage(navController) }
            composable(Screen.Profile.route) { ProfilePage(navController) }
            composable(Screen.MovieDetail.route) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: "Unknown"
                val genre = backStackEntry.arguments?.getString("genre") ?: "Unknown"
                val rating = backStackEntry.arguments?.getString("rating")?.toDoubleOrNull() ?: 0.0
                val imageResId = backStackEntry.arguments?.getString("imageResId")?.toIntOrNull() ?: 0
                MovieDetailScreen(title, genre, rating, imageResId, navController)
            }
            composable("all_movies/{title}") { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: "Unknown"
                AllMoviesScreen(navController, title)
            }
        }

    }

}

@Composable
fun HomePage(navController: NavController) {
    Column(
        modifier = Modifier.padding(start = 26.dp)
    ) {
        Spacer(modifier = Modifier.height(55.dp))
        Image(
            painter = painterResource(id = R.drawable.photo_5271554147017156476_m),
            contentDescription = null,
            modifier = Modifier.size(120.dp, 18.dp).padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.height(46.88.dp))
        LazyColumn {
            item { CardTitle("Премьеры", movies = movieList, navController) }
            item { CardTitle("Популярные", movies = movieList, navController) }
            item { CardTitle("Боевики США", movies = movieList, navController) }
            item { CardTitle("Топ-250", movies = movieList, navController) }
            item { CardTitle("Драмы Франция", movies = movieList, navController) }
            item { CardTitle("Сериалы", movies = movieList, navController) }
        }
        Spacer(modifier = Modifier.height(36.12.dp))
    }
}

@Composable
fun CardTitle(title: String, movies: List<MovieItem>, navController: NavController) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = Color(0xff272727),
                modifier = Modifier.height(20.dp).padding(start = 20.dp)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = "Все",
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 15.4.sp,
                color = Color(0xff3d3bff),
                modifier = Modifier
                    .height(15.dp)
                    .padding(end = 26.dp)
                    .clickable {
                        navController.navigate("all_movies/$title")
                    }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(movies.size) { index ->
                val movie = movies[index]
                MovieCard(movie = movie) {
                    navController.navigate(
                        Screen.MovieDetail.createRoute(
                            movie.title,
                            movie.genre,
                            movie.rating,
                            movie.imageResId
                        )
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(111.dp, 156.dp)
                        .clickable {
                            navController.navigate("all_movies/$title")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.photo_5271554147017156474_x),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp, 32.dp)
                        )
                        Text(text = "Показать все", fontSize = 12.sp)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    Box(
        modifier = Modifier
            .size(400.dp, 90.dp)
            .background(
                Color(0x3D3BFF).copy(alpha = 0.03f),
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(400.dp, 90.dp)
                .padding(top = 3.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            NavigationBar(
                modifier = Modifier.padding(horizontal = 35.dp),
                containerColor = Color.Transparent,
                tonalElevation = 0.dp
            ) {
                val items = listOf(Screen.Home, Screen.Search, Screen.Profile)
                val currentRoute = navController.currentBackStackEntry?.destination?.route

                items.forEach { screen ->
                    val isSelected = currentRoute == screen.route

                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = screen.route,
                                modifier = Modifier.size(24.dp),
                                tint = if (isSelected) Color.Blue else Color.Gray
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            if (!isSelected) {
                                navController.navigate(screen.route) {
                                    launchSingleTop = true
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Blue,
                            unselectedIconColor = Color.Gray
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun MovieDetailScreen(title: String, genre: String, rating: Double, imageResId: Int, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back1),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Movie Details", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Title: $title", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "Genre: $genre", fontSize = 20.sp, color = Color.Gray)
        Text(text = "Rating: $rating", fontSize = 20.sp, color = Color.Blue)
    }
}


@Composable
fun SearchPage(navController: NavController) {
    Text(text = "Search Screen")
}

@Composable
fun ProfilePage(navController: NavController) {
    Text(text = "Profile Screen")
}

data class MovieItem(val title: String, val genre: String, val rating: Double, val imageResId: Int)


val movieList = listOf(
    MovieItem("1+1", "Драма", 7.8, R.drawable.photo_5271554147017156471_y),
    MovieItem("Шоушенк", "Боевик", 6.5, R.drawable.photo_5271554147017156472_x),
    MovieItem("Шал", "Исторический", 8.1, R.drawable.photo_5271554147017156475_y),
    MovieItem("1+1", "Драма", 7.8, R.drawable.photo_5271554147017156471_y),
    MovieItem("Шоушенк", "Боевик", 6.5, R.drawable.photo_5271554147017156472_x),
    MovieItem("Шал", "Исторический", 8.1, R.drawable.photo_5271554147017156475_y),
    MovieItem("1+1", "Драма", 7.8, R.drawable.photo_5271554147017156471_y),
    MovieItem("Шоушенк", "Боевик", 6.5, R.drawable.photo_5271554147017156472_x),
    MovieItem("Шал", "Исторический", 8.1, R.drawable.photo_5271554147017156475_y),
    MovieItem("1+1", "Драма", 7.8, R.drawable.photo_5271554147017156471_y),
    MovieItem("Шоушенк", "Боевик", 6.5, R.drawable.photo_5271554147017156472_x),
    MovieItem("Шал", "Исторический", 8.1, R.drawable.photo_5271554147017156475_y))


@Composable
fun MovieCard(movie: MovieItem, onMovieClick: () -> Unit) {
    Column(
        modifier = Modifier
        .clickable { onMovieClick() }

                ) {
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
        )            }
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
@Composable
fun AllMoviesScreen(navController: NavController, title: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        androidx.compose.material.TopAppBar(
            title = { Text(text = "Все фильмы \"$title\"") },
            navigationIcon = {
                androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back1),
                        contentDescription = "Назад"
                    )
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black,
            elevation = 4.dp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.widthIn(max = 300.dp)
            ) {
                items(movieList) { movie ->
                    MovieCard(movie = movie) {
                        navController.navigate(
                            Screen.MovieDetail.createRoute(
                                movie.title,
                                movie.genre,
                                movie.rating,
                                movie.imageResId
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}



