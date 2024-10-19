package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.ui.theme.Assignment2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme{
                MyScrean()
            }
        }
    }
}


sealed class Screen(val route: String, val icon: Int){
    object Home: Screen("home", R.drawable.home)
    object Search: Screen("search", R.drawable.search)
    object Profile: Screen("profile", R.drawable.profile)
}




@Composable

fun MyScrean () {
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
        }
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
                            if(!isSelected) {
                                navController.navigate(screen.route){
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
fun HomePage(navController: NavController){

}
@Composable
fun SearchPage(navController: NavController){

}

@Composable
fun ProfilePage(navController: NavController){

}